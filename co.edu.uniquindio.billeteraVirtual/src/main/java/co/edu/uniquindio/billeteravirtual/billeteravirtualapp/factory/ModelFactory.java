package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.factory;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.CuentaDto;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.TransaccionDto;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.UsuarioDto;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.mappers.CuentaMappingImpl;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.mappers.TransaccionMapplingImpl;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.mappers.UsuarioMappingImpl;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.Administrador;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.BilleteraVirtual;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.Cuenta;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.Usuario;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.service.ICuentaMapping;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.service.IModelFactoryService;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.service.ITransaccionMapping;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.service.IUsuarioMapping;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.utils.DataUtil;

import java.util.LinkedList;

public class ModelFactory implements IModelFactoryService {
    private static ModelFactory modelFactory;
    private BilleteraVirtual billeteraVirtual;
    private Administrador administrador;
    private IUsuarioMapping usuarioMapper;
    private ICuentaMapping cuentaMapper;
    private ITransaccionMapping transaccionMapper;

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

    public UsuarioDto obtenerUsuario(String id) {
        return usuarioMapper.usuarioToUsuarioDto(administrador.obtenerUsuario(id));
    }

    @Override
    public LinkedList<CuentaDto> obtenerCuentas() {
        return cuentaMapper.getCuentasDto(billeteraVirtual.getListaCuentas());
    }

    public boolean agregarCuenta(CuentaDto cuenta) {
        return administrador.agregarCuenta(cuentaMapper.cuentaDtoToCuenta(cuenta, billeteraVirtual),
                cuenta.idUsuarioAsociado());
    }

    public boolean eliminarCuenta(int idCuenta, String numCuenta) {
        return administrador.eliminarCuenta(idCuenta, numCuenta);
    }

    public boolean actualizarCuenta(CuentaDto cuentaVieja, CuentaDto cuentaNueva) {
        return administrador.actualizarCuenta(cuentaMapper.cuentaDtoToCuenta(cuentaVieja, billeteraVirtual),
                cuentaVieja.idUsuarioAsociado(), cuentaNueva.idUsuarioAsociado(),
                cuentaMapper.cuentaDtoToCuenta(cuentaNueva, billeteraVirtual));
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
        return billeteraVirtual.obtenerUsuario(idUsuario).
                agregarCuenta(cuentaMapper.cuentaDtoToCuenta(cuentaDto, billeteraVirtual));
    }

    public boolean actualizarCuentaUsuario(String idUsuario, CuentaDto cuentaVieja, CuentaDto cuentaNueva) {
        return billeteraVirtual.obtenerUsuario(idUsuario).
                actualizarCuenta(cuentaMapper.cuentaDtoToCuenta(cuentaVieja, billeteraVirtual),
                        cuentaMapper.cuentaDtoToCuenta(cuentaNueva, billeteraVirtual));
    }

    public boolean eliminarCuentaUsuario(String idUsuario, int idCuenta, String numCuenta) {
        return billeteraVirtual.obtenerUsuario(idUsuario).eliminarCuenta(idCuenta, numCuenta);
    }

    public LinkedList<CuentaDto> obtenerCuentasUsuario(String idUsuario) {
        return cuentaMapper.getCuentasDto(billeteraVirtual.obtenerUsuario(idUsuario).getListaCuentas());
    }

    public LinkedList<String> obtenerCategoriasNombresDeUsuario(String idUsuario) {
        return billeteraVirtual.obtenerCategoriasNombresDeUsuario(idUsuario);
    }

    public LinkedList<String> obtenerNumCuentasUsuario(String idUsuario) {
        return billeteraVirtual.obtenerNumCuentasUsuario(idUsuario);
    }

    public int obtenerNuevoIdTransaccion() {
        return billeteraVirtual.obtenerNuevoIdTransaccion();
    }

    public boolean agregarTransaccion(TransaccionDto transaccion, String idUsuario) {
        return billeteraVirtual.obtenerUsuario(idUsuario).
                agregarTransaccion(transaccionMapper.transaccionDtoToTransaccion(transaccion, billeteraVirtual,
                        billeteraVirtual.obtenerUsuario(idUsuario),
                        billeteraVirtual.obtenerCategoriaPorNombre(transaccion.nombreCategoria()),
                        billeteraVirtual.obtenerCuentaNumCuenta(transaccion.numCuentaOrigen()),
                        billeteraVirtual.obtenerCuentaNumCuenta(transaccion.numCuentaDestino())));
    }
}