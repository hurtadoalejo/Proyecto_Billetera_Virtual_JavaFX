package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.service.ICrudUsuario;

public class Administrador implements ICrudUsuario {
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
    public boolean crearUsuario(Usuario usuario) {
        return billeteraVirtual.crearUsuario(usuario);
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
}