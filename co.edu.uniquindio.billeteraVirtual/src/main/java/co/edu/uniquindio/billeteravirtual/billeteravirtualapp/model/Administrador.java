package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.service.ICrudCuenta;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.service.ICrudUsuario;

public class Administrador implements ICrudUsuario, ICrudCuenta {
    private BilleteraVirtual billeteraVirtual;
    private int clave;

    public Administrador(BilleteraVirtual billeteraVirtual, int clave) {
        this.billeteraVirtual = billeteraVirtual;
        this.clave = clave;
    }

    public int getClave() {
        return clave;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }

    @Override
    public boolean agregarUsuario(Usuario usuario) {
        return billeteraVirtual.agregarUsuario(usuario);
    }

    @Override
    public boolean eliminarUsuario(String id) {
        return billeteraVirtual.eliminarUsuario(id);
    }

    @Override
    public boolean actualizarUsuario(String id, Usuario nuevoUsuario) {
        return billeteraVirtual.actualizarUsuario(id, nuevoUsuario);
    }

    @Override
    public Usuario obtenerUsuario(String id) {
        return billeteraVirtual.obtenerUsuario(id);
    }

    @Override
    public boolean agregarCuenta(Cuenta cuenta, String idUsuario) {
        return billeteraVirtual.agregarCuenta(cuenta, idUsuario);
    }

    @Override
    public boolean eliminarCuenta(int id, String numCuenta) {
        return billeteraVirtual.eliminarCuenta(id, numCuenta);
    }

    @Override
    public boolean actualizarCuenta(Cuenta cuentaVieja, String idUsuarioViejo, String idUsuarioNuevo,Cuenta nuevaCuenta) {
        return billeteraVirtual.actualizarCuenta(cuentaVieja, idUsuarioViejo, idUsuarioNuevo, nuevaCuenta);
    }

    @Override
    public Cuenta obtenerCuenta(int id, String numCuenta) {
        return billeteraVirtual.obtenerCuenta(id, numCuenta);
    }
}