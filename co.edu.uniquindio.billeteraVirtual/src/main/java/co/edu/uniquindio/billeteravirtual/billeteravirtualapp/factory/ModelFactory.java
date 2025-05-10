package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.factory;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.*;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.mappers.BilleteraMappingImpl;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.*;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.service.*;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.utils.DataUtil;

import java.time.LocalDate;
import java.util.LinkedList;

public class ModelFactory implements IModelFactoryService {
    private static ModelFactory modelFactory;
    private BilleteraVirtual billeteraVirtual;
    private Administrador administrador;
    private IBilleteraMapping billeteraMapper;

    public static ModelFactory getInstance(){
        if (modelFactory == null){
            modelFactory = new ModelFactory();
        }
        return modelFactory;
    }

    private ModelFactory(){
        billeteraMapper = new BilleteraMappingImpl();
        billeteraVirtual = DataUtil.inicializarDatos();
        administrador = billeteraVirtual.getAdministrador();
    }

    public boolean verificarClaveAdmin(int clave) {
        return billeteraVirtual.verificarClaveAdmin(clave);
    }

    public boolean verificarCredencialesUsuario(String id, int clave) {
        return billeteraVirtual.verificarCredencialesUsuario(id, clave);
    }

    @Override
    public LinkedList<UsuarioDto> obtenerUsuarios() {
        return billeteraMapper.getUsuariosDto(billeteraVirtual.getListaUsuarios());
    }

    public boolean agregarUsuario(UsuarioDto usuario) {
        return administrador.agregarUsuario(usuarioDtoToUsuario(usuario));
    }

    public boolean eliminarUsuario(String id) {
        return administrador.eliminarUsuario(id);
    }

    public boolean actualizarUsuario(String id, UsuarioDto usuarioNuevo) {
        return administrador.actualizarUsuario(id, usuarioDtoToUsuario(usuarioNuevo));
    }

    public UsuarioDto obtenerUsuarioToUsuarioDto(String id) {
        return billeteraMapper.usuarioToUsuarioDto(administrador.obtenerUsuario(id));
    }

    @Override
    public LinkedList<CuentaDto> obtenerCuentas() {
        return billeteraMapper.getCuentasDto(billeteraVirtual.getListaCuentas());
    }

    public boolean agregarCuenta(CuentaDto cuenta) {
        return administrador.agregarCuenta(cuentaDtoToCuenta(cuenta));
    }

    public boolean eliminarCuenta(int idCuenta, String numCuenta) {
        return administrador.eliminarCuenta(idCuenta, numCuenta);
    }

    public boolean actualizarCuenta(CuentaDto cuentaVieja, CuentaDto cuentaNueva) {
        return administrador.actualizarCuenta(cuentaDtoToCuenta(cuentaVieja), cuentaVieja.idUsuarioAsociado(),
                cuentaNueva.idUsuarioAsociado(), cuentaDtoToCuenta(cuentaNueva));
    }

    public LinkedList<String> obtenerUsuariosId() {
        return billeteraVirtual.obtenerUsuariosId();
    }

    public boolean verificarCuentaId(int id) {
        return billeteraVirtual.verificarCuentaId(id);
    }

    public boolean verificarCuentaNumCuenta(String numCuenta) {
        return billeteraVirtual.verificarCuentaNumCuenta(numCuenta);
    }

    public boolean agregarCuentaUsuario(String idUsuario, CuentaDto cuentaDto) {
        return obtenerUsuario(idUsuario).agregarCuenta(cuentaDtoToCuenta(cuentaDto));
    }

    public boolean actualizarCuentaUsuario(String idUsuario, CuentaDto cuentaVieja, CuentaDto cuentaNueva) {
        return obtenerUsuario(idUsuario).actualizarCuenta(cuentaDtoToCuenta(cuentaVieja),
                cuentaDtoToCuenta(cuentaNueva));
    }

    public boolean eliminarCuentaUsuario(String idUsuario, int idCuenta, String numCuenta) {
        return obtenerUsuario(idUsuario).eliminarCuenta(idCuenta, numCuenta);
    }

    public LinkedList<CuentaDto> obtenerCuentasUsuario(String idUsuario) {
        return billeteraMapper.getCuentasDto(obtenerUsuario(idUsuario).getListaCuentas());
    }

    public LinkedList<String> obtenerNumCuentasUsuario(String idUsuario) {
        return billeteraVirtual.obtenerNumCuentasUsuario(idUsuario);
    }

    public int obtenerNuevoIdTransaccion() {
        return billeteraVirtual.obtenerNuevoIdTransaccion();
    }

    public boolean agregarTransaccion(TransaccionDto transaccion, String idUsuario) {
        return obtenerUsuario(idUsuario).
                agregarTransaccion(transaccionDtoToTransaccion(transaccion));
    }

    public boolean saldoCuentaEsSuficiente(TransaccionDto transaccion) {
        return billeteraVirtual.validarSaldo(transaccionDtoToTransaccion(transaccion));
    }

    public boolean agregarCategoria(CategoriaDto categoria, String idUsuario) {
        return obtenerUsuario(idUsuario).
                agregarCategoria(categoriaDtoToCategoria(categoria));
    }

    public boolean eliminarCategoria(String idUsuario, int idCategoria) {
        return obtenerUsuario(idUsuario).eliminarCategoria(idCategoria);
    }

    public boolean actualizarCategoria(String idUsuario, int idCategoria, CategoriaDto nuevaCategoria){
        return obtenerUsuario(idUsuario).actualizarCategoria(idCategoria, categoriaDtoToCategoria(nuevaCategoria));
    }

    private Usuario obtenerUsuario(String idUsuario) {
        return billeteraVirtual.obtenerUsuario(idUsuario);
    }

    private Categoria obtenerCategoria(int idCategoria) {
        return billeteraVirtual.obtenerCategoria(idCategoria);
    }

    private Categoria obtenerCategoriaPorNombre(String nombreCategoria) {
        return billeteraVirtual.obtenerCategoriaPorNombre(nombreCategoria);
    }

    private Presupuesto obtenerPresupuesto(int idPresupuesto) {
        return billeteraVirtual.obtenerPresupuesto(idPresupuesto);
    }

    public LinkedList<String> obtenerPresupuestoDisponiblesNombres(String idUsuario) {
        return obtenerUsuario(idUsuario).obtenerPresupuestosDisponiblesNombres();
    }

    private Presupuesto obtenerPresupuestoNombre(String nombrePresupuesto, String idUsuario) {
        return obtenerUsuario(idUsuario).obtenerPresupuestoNombre(nombrePresupuesto);
    }

    private Cuenta obtenerCuentaNumCuenta(String numCuenta) {
        return billeteraVirtual.obtenerCuentaNumCuenta(numCuenta);
    }

    @Override
    public LinkedList<CategoriaDto> obtenerCategorias(String idUsuario) {
        return billeteraMapper.getListaCategorias(obtenerUsuario(idUsuario).getListaCategorias());
    }

    public LinkedList<String> obtenerPresupuestosNombres(String idUsuario) {
        return obtenerUsuario(idUsuario).obtenerPresupuestosNombres();
    }

    public LinkedList<String> obtenerPresupuestosDisponiblesUsuario(String idUsuario) {
        return obtenerUsuario(idUsuario).obtenerPresupuestosDisponiblesNombres();
    }

    public boolean validarPresupuesto(TransaccionDto transaccion) {
        return billeteraVirtual.validarPresupuesto(transaccionDtoToTransaccion(transaccion));
    }

    public boolean agregarPresupuesto(PresupuestoDto presupuestoDto) {
        return obtenerUsuario(presupuestoDto.idUsuario())
                .agregarPresupuesto(presupuestoDtoToPresupuesto(presupuestoDto));
    }

    public boolean eliminarPresupuesto(int idPresupuesto, String idUsuario) {
        return obtenerUsuario(idUsuario).eliminarPresupuesto(idPresupuesto);
    }

    public boolean actualizarPresupuesto(int idPresupuestoViejo, PresupuestoDto presupuestoDto) {
        return obtenerUsuario(presupuestoDto.idUsuario())
                .actualizarPresupuesto(idPresupuestoViejo, presupuestoDtoToPresupuesto(presupuestoDto));
    }

    private Usuario usuarioDtoToUsuario(UsuarioDto usuarioDto) {
        return billeteraMapper.usuarioDtoToUsuario(usuarioDto, billeteraVirtual);
    }

    private Cuenta cuentaDtoToCuenta(CuentaDto cuentaDto) {
        return billeteraMapper.cuentaDtoToCuenta(cuentaDto, billeteraVirtual,
                obtenerUsuario(cuentaDto.idUsuarioAsociado()),
                obtenerPresupuestoNombre(cuentaDto.presupuestoAsociado(), cuentaDto.idUsuarioAsociado()));
    }

    private Transaccion transaccionDtoToTransaccion(TransaccionDto transaccion){
        return billeteraMapper.transaccionDtoToTransaccion(transaccion, billeteraVirtual,
                obtenerUsuario(transaccion.idUsuario()),
                obtenerCuentaNumCuenta(transaccion.numCuentaOrigen()),
                obtenerCuentaNumCuenta(transaccion.numCuentaDestino()));
    }

    private Categoria categoriaDtoToCategoria(CategoriaDto categoriaDto) {
        return billeteraMapper.categoriaDtoToCategoria(categoriaDto, billeteraVirtual,
                obtenerUsuario(categoriaDto.idUsuario()));
    }

    private Presupuesto presupuestoDtoToPresupuesto(PresupuestoDto presupuestoDto) {
        return billeteraMapper.presupuestoDtoToPresupuesto(presupuestoDto, billeteraVirtual,
                obtenerUsuario(presupuestoDto.idUsuario()),
                obtenerCuentaNumCuenta(presupuestoDto.cuentaAsociada()),
                obtenerCategoriaPorNombre(presupuestoDto.categoria()));
    }

    public LinkedList<PresupuestoDto> obtenerPresupuestos(String idUsuario) {
        return billeteraMapper.getPresupuestosDto(obtenerUsuario(idUsuario).getListaPresupuestos());
    }


    public LinkedList<TransaccionDto> obtenerTransacciones(String idUsuario) {
        return billeteraMapper.getTransaccionDto(obtenerUsuario(idUsuario).getListaTransacciones());
    }

    public LinkedList<TransaccionDto> obtenerTransacciones() {
        return billeteraMapper.getTransaccionDto(billeteraVirtual.getListaTransacciones());
    }

    public LinkedList<TransaccionDto> obtenerListaTransaccionesGastos(String idUsuario, LocalDate fechaInicio, LocalDate fechaFin) {
        return billeteraMapper.getTransaccionDto(obtenerUsuario(idUsuario).obtenerListaTransaccionesGastos(fechaInicio, fechaFin));
    }

    public LinkedList<TransaccionDto> obtenerListaTransaccionesIngresos(String idUsuario, LocalDate fechaInicio, LocalDate fechaFin) {
        return billeteraMapper.getTransaccionDto(obtenerUsuario(idUsuario).obtenerListaTransaccionesIngresos(fechaInicio, fechaFin));
    }

    public LinkedList<String> obtenerCategoriasDisponibles(String idUsuario) {
        return obtenerUsuario(idUsuario).obtenerCategoriasDisponibles();
    }

    public UsuarioDto obtenerUsuarioMasTransacciones() {
        return billeteraMapper.usuarioToUsuarioDto(billeteraVirtual.obtenerUsuarioMasTransacciones());
    }

    public double obtenerSaldoPromedioUsuarios() {
        return billeteraVirtual.obtenerSaldoPromedioUsuarios();
    }
}