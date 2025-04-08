package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.factory;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.CuentaDto;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.UsuarioDto;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.mappers.CuentaMappingImpl;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.mappers.UsuarioMappingImpl;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.Administrador;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.BilleteraVirtual;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.Usuario;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.service.ICuentaMapping;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.service.IModelFactoryService;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.service.IUsuarioMapping;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.utils.DataUtil;

import java.util.LinkedList;

public class ModelFactory implements IModelFactoryService {
    private static ModelFactory modelFactory;
    private BilleteraVirtual billeteraVirtual;
    private Administrador administrador;
    private IUsuarioMapping usuarioMapper;
    private ICuentaMapping cuentaMapper;

    public static ModelFactory getInstance(){
        if (modelFactory == null){
            modelFactory = new ModelFactory();
        }
        return modelFactory;
    }

    private ModelFactory(){
        usuarioMapper = new UsuarioMappingImpl();
        cuentaMapper = new CuentaMappingImpl();
        billeteraVirtual = DataUtil.inicializarDatos();
        administrador = billeteraVirtual.getAdministrador();
    }

    public boolean verificarClaveAdmin(int clave) {
        return billeteraVirtual.verificarClaveAdmin(clave);
    }

    public Usuario verificarCredencialesUsuario(String id, int clave) {
        return billeteraVirtual.verificarCredencialesUsuario(id, clave);
    }

    @Override
    public LinkedList<UsuarioDto> obtenerUsuarios() {
        return usuarioMapper.getUsuariosDto(billeteraVirtual.getListaUsuarios());
    }

    public boolean agregarUsuario(UsuarioDto usuario) {
        return administrador.agregarUsuario(usuarioMapper.usuarioDtoToUsuario(usuario));
    }

    public boolean eliminarUsuario(UsuarioDto usuario) {
        return administrador.eliminarUsuario(usuario.idUsuario());
    }

    public boolean actualizarUsuario(UsuarioDto usuario, UsuarioDto usuarioNuevo) {
        return administrador.actualizarUsuario(usuario.idUsuario(), usuarioMapper.usuarioDtoToUsuario(usuarioNuevo));
    }

    public Usuario obtenerUsuario(String id) {
        return administrador.obtenerUsuario(id);
    }

    @Override
    public LinkedList<CuentaDto> obtenerCuentas() {
        return cuentaMapper.getCuentasDto(billeteraVirtual.getListaCuentas());
    }

    public boolean agregarCuenta(CuentaDto cuenta) {
        return administrador.agregarCuenta(cuentaMapper.cuentaDtoToCuenta(cuenta, obtenerUsuario(cuenta.idUsuarioAsociado())));
    }

    public boolean eliminarCuenta(CuentaDto cuenta) {
        return administrador.eliminarCuenta(cuenta.idCuenta(), cuenta.numCuenta());
    }

    public boolean actualizarCuenta(int id, String numCuenta, CuentaDto cuentaNueva) {
        return administrador.actualizarCuenta(id, numCuenta, cuentaMapper.cuentaDtoToCuenta(cuentaNueva, obtenerUsuario(cuentaNueva.idUsuarioAsociado())));
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
}