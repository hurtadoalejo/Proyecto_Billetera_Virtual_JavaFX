package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.factory;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.CategoriaDto;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.CuentaDto;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.TransaccionDto;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.UsuarioDto;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.mappers.CategoriaMappingImpl;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.mappers.CuentaMappingImpl;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.mappers.TransaccionMapplingImpl;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.mappers.UsuarioMappingImpl;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.*;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.service.*;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.utils.DataUtil;

import java.util.LinkedList;

public class ModelFactory implements IModelFactoryService {
    private static ModelFactory modelFactory;
    private BilleteraVirtual billeteraVirtual;
    private Administrador administrador;
    private IUsuarioMapping usuarioMapper;
    private ICuentaMapping cuentaMapper;
    private ITransaccionMapping transaccionMapper;
    private ICategoriaMapping categoriaMapper;

    /**
     * Metodo para obtener la instancia unica de la clase ModelFactory
     * @return Instancia unica de la clase ModelFactory
     */
    public static ModelFactory getInstance(){
        if (modelFactory == null){
            modelFactory = new ModelFactory();
        }
        return modelFactory;
    }

    /**
     * Metodo constructor de la clase ModelFactory
     */
    private ModelFactory(){
        usuarioMapper = new UsuarioMappingImpl();
        cuentaMapper = new CuentaMappingImpl();
        transaccionMapper = new TransaccionMapplingImpl();
        categoriaMapper = new CategoriaMappingImpl();
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
        return usuarioMapper.getUsuariosDto(billeteraVirtual.getListaUsuarios());
    }

    public boolean agregarUsuario(UsuarioDto usuario) {
        return administrador.agregarUsuario(usuarioMapper.usuarioDtoToUsuario(usuario, billeteraVirtual));
    }

    public boolean eliminarUsuario(String id) {
        return administrador.eliminarUsuario(id);
    }

    public boolean actualizarUsuario(String id, UsuarioDto usuarioNuevo) {
        return administrador.actualizarUsuario(id, usuarioMapper.usuarioDtoToUsuario(usuarioNuevo, billeteraVirtual));
    }

    public UsuarioDto obtenerUsuarioToUsuarioDto(String id) {
        return usuarioMapper.usuarioToUsuarioDto(administrador.obtenerUsuario(id));
    }

    @Override
    public LinkedList<CuentaDto> obtenerCuentas() {
        return cuentaMapper.getCuentasDto(billeteraVirtual.getListaCuentas());
    }

    public boolean agregarCuenta(CuentaDto cuenta) {
        return administrador.agregarCuenta(cuentaMapper.cuentaDtoToCuenta(cuenta, billeteraVirtual,
                        obtenerUsuario(cuenta.idUsuarioAsociado())));
    }

    public boolean eliminarCuenta(int idCuenta, String numCuenta) {
        return administrador.eliminarCuenta(idCuenta, numCuenta);
    }

    public boolean actualizarCuenta(CuentaDto cuentaVieja, CuentaDto cuentaNueva) {
        return administrador.actualizarCuenta(cuentaMapper.cuentaDtoToCuenta(cuentaVieja, billeteraVirtual,
                        obtenerUsuario(cuentaVieja.idUsuarioAsociado())),
                cuentaVieja.idUsuarioAsociado(), cuentaNueva.idUsuarioAsociado(),
                cuentaMapper.cuentaDtoToCuenta(cuentaNueva, billeteraVirtual,
                        obtenerUsuario(cuentaNueva.idUsuarioAsociado())));
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
        return obtenerUsuario(idUsuario).
                agregarCuenta(cuentaMapper.cuentaDtoToCuenta(cuentaDto, billeteraVirtual,
                        obtenerUsuario(idUsuario)));
    }

    public boolean actualizarCuentaUsuario(String idUsuario, CuentaDto cuentaVieja, CuentaDto cuentaNueva) {
        return obtenerUsuario(idUsuario).
                actualizarCuenta(cuentaMapper.cuentaDtoToCuenta(cuentaVieja, billeteraVirtual,
                                obtenerUsuario(idUsuario)),
                        cuentaMapper.cuentaDtoToCuenta(cuentaNueva, billeteraVirtual,
                                obtenerUsuario(idUsuario)));
    }

    public boolean eliminarCuentaUsuario(String idUsuario, int idCuenta, String numCuenta) {
        return obtenerUsuario(idUsuario).eliminarCuenta(idCuenta, numCuenta);
    }

    public LinkedList<CuentaDto> obtenerCuentasUsuario(String idUsuario) {
        return cuentaMapper.getCuentasDto(obtenerUsuario(idUsuario).getListaCuentas());
    }

    public LinkedList<String> obtenerCategoriasNombresDeUsuario(String idUsuario) {
        return obtenerUsuario(idUsuario).obtenerCategoriasNombres();
    }

    public LinkedList<String> obtenerNumCuentasUsuario(String idUsuario) {
        return billeteraVirtual.obtenerNumCuentasUsuario(idUsuario);
    }

    public int obtenerNuevoIdTransaccion() {
        return billeteraVirtual.obtenerNuevoIdTransaccion();
    }

    public boolean agregarTransaccion(TransaccionDto transaccion, String idUsuario) {
        return obtenerUsuario(idUsuario).
                agregarTransaccion(transaccionDtoToTransaccion(transaccion, idUsuario));
    }

    public boolean saldoCuentaEsSuficiente(TransaccionDto transaccion, String idUsuario) {
        return obtenerUsuario(idUsuario).
                saldoCuentaEsSuficiente(transaccionDtoToTransaccion(transaccion, idUsuario));
    }

    private Transaccion transaccionDtoToTransaccion(TransaccionDto transaccion, String idUsuario){
        return transaccionMapper.transaccionDtoToTransaccion(transaccion, billeteraVirtual,
                obtenerUsuario(idUsuario),
                obtenerCategoriaPorNombre(transaccion.nombreCategoria()),
                obtenerCuentaNumCuenta(transaccion.numCuentaOrigen()),
                obtenerCuentaNumCuenta(transaccion.numCuentaDestino()));
    }

    public boolean cuentasExisten(String numCuentaOrigen, String numCuentaDestino) {
        return billeteraVirtual.cuentasExisten(numCuentaOrigen, numCuentaDestino);
    }

    public boolean agregarCategoria(CategoriaDto categoria, String idUsuario) {
        return obtenerUsuario(idUsuario).
                agregarCategoria(categoriaMapper.categoriaDtoToCategoria(categoria, billeteraVirtual,
                        obtenerUsuario(idUsuario),
                        obtenerPresupuestoNombre(categoria.nombrePresupuesto(), idUsuario)));
    }

    public boolean eliminarCategoria(String idUsuario, int idCategoria) {
        return obtenerUsuario(idUsuario).eliminarCategoria(idCategoria);
    }

    public boolean actualizarCategoria(String idUsuario, int idCategoria, CategoriaDto nuevaCategoria){
        return obtenerUsuario(idUsuario).actualizarCategoria(idCategoria, categoriaMapper.categoriaDtoToCategoria(nuevaCategoria, billeteraVirtual, obtenerUsuario(idUsuario),
                obtenerPresupuestoNombre(nuevaCategoria.nombrePresupuesto(), idUsuario)));
    }

    private Usuario obtenerUsuario(String idUsuario) {
        return billeteraVirtual.obtenerUsuario(idUsuario);
    }

    private Categoria obtenerCategoria(int idCategoria) {
        return billeteraVirtual.obtenerCategoria(idCategoria);
    }

    private Presupuesto obtenerPresupuesto(int idPresupuesto) {
        return billeteraVirtual.obtenerPresupuesto(idPresupuesto);
    }

    private Presupuesto obtenerPresupuestoNombre(String nombrePresupuesto, String idUsuario) {
        return obtenerUsuario(idUsuario).obtenerPresupuestoNombre(nombrePresupuesto);
    }

    private Categoria obtenerCategoriaPorNombre(String nombreCategoria) {
        return billeteraVirtual.obtenerCategoriaPorNombre(nombreCategoria);
    }

    private Cuenta obtenerCuentaNumCuenta(String numCuenta) {
        return billeteraVirtual.obtenerCuentaNumCuenta(numCuenta);
    }

    @Override
    public LinkedList<CategoriaDto> obtenerCategorias(String idUsuario) {
        return categoriaMapper.getListaCategorias(obtenerUsuario(idUsuario).getListaCategorias());
    }

    public LinkedList<String> obtenerPresupuestosNombres(String idUsuario) {
        return obtenerUsuario(idUsuario).obtenerPresupuestosNombres();
    }
}