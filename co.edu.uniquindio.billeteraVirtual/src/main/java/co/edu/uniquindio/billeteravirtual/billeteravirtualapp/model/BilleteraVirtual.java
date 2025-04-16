package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.service.*;

import java.util.LinkedList;

public class BilleteraVirtual implements ICrudUsuario, ICrudCuenta, ICrudCategoria, ICrudPresupuesto, ICrudTransaccion {
    private String nombre;
    private Administrador administrador;
    private LinkedList<Categoria> listaCategorias;
    private LinkedList<Presupuesto> listaPresupuestos;
    private LinkedList<Transaccion> listaTransacciones;
    private LinkedList<Cuenta> listaCuentas;
    private LinkedList<Usuario> listaUsuarios;

    public BilleteraVirtual(String nombre){
        this.nombre = nombre;
        this.administrador = new Administrador(this, 2911);
        this.listaCategorias = new LinkedList<>();
        this.listaPresupuestos = new LinkedList<>();
        this.listaTransacciones = new LinkedList<>();
        this.listaCuentas = new LinkedList<>();
        this.listaUsuarios = new LinkedList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public LinkedList<Categoria> getListaCategorias() {
        return listaCategorias;
    }

    public void setListaCategorias(LinkedList<Categoria> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    public LinkedList<Presupuesto> getListaPresupuestos() {
        return listaPresupuestos;
    }

    public void setListaPresupuestos(LinkedList<Presupuesto> listaPresupuestos) {
        this.listaPresupuestos = listaPresupuestos;
    }

    public LinkedList<Transaccion> getListaTransacciones() {
        return listaTransacciones;
    }

    public void setListaTransacciones(LinkedList<Transaccion> listaTransacciones) {
        this.listaTransacciones = listaTransacciones;
    }

    public LinkedList<Cuenta> getListaCuentas() {
        return listaCuentas;
    }

    public void setListaCuentas(LinkedList<Cuenta> listaCuentas) {
        this.listaCuentas = listaCuentas;
    }

    public LinkedList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(LinkedList<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    @Override
    public boolean agregarUsuario(Usuario usuario) {
        if (obtenerUsuario(usuario.getIdUsuario()) == null){
            listaUsuarios.add(usuario);
            return true;
        }
        return false;
    }

    @Override
    public boolean eliminarUsuario(String id) {
        Usuario usuario = obtenerUsuario(id);
        if (usuario != null) {
            listaUsuarios.remove(usuario);
            eliminarCuentasUsuario(usuario);
            eliminarPresupuestosUsuario(usuario);
            eliminarCategoriasUsuario(usuario);
            return true;
        }
        return false;
    }

    private void eliminarCategoriasUsuario(Usuario usuario) {
        for (Categoria categoria : listaCategorias) {
            listaCategorias.remove(categoria);
        }
    }

    private void eliminarCuentasUsuario(Usuario usuario) {
        for (Cuenta cuenta : usuario.getListaCuentas()) {
            listaCuentas.remove(cuenta);
        }
    }

    private void eliminarPresupuestosUsuario(Usuario usuario) {
        for (Presupuesto presupuesto : usuario.getListaPresupuestos()) {
            listaPresupuestos.remove(presupuesto);
        }
    }

    @Override
    public boolean actualizarUsuario(String id, Usuario nuevoUsuario) {
        for (Usuario usuario : listaUsuarios){
            if (usuario.getIdUsuario().equalsIgnoreCase(id)){
                if (obtenerUsuario(nuevoUsuario.getIdUsuario()) == null || nuevoUsuario.getIdUsuario().equalsIgnoreCase(id)){
                    usuario.setIdUsuario(nuevoUsuario.getIdUsuario());
                    usuario.setClave(nuevoUsuario.getClave());
                    usuario.setNombreCompleto(nuevoUsuario.getNombreCompleto());
                    usuario.setDireccion(nuevoUsuario.getDireccion());
                    usuario.setCorreoElectronico(nuevoUsuario.getCorreoElectronico());
                    usuario.setNumeroTelefono(nuevoUsuario.getNumeroTelefono());
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Usuario obtenerUsuario(String id) {
        for (Usuario usuario : listaUsuarios){
            if (usuario.getIdUsuario().equalsIgnoreCase(id)){
                return usuario;
            }
        }
        return null;
    }


    @Override
    public boolean agregarCuenta(Cuenta cuenta) {
        if (obtenerCuenta(cuenta.getIdCuenta(), cuenta.getNumeroCuenta()) == null){
            listaCuentas.add(cuenta);
            cuenta.getUsuarioAsociado().getListaCuentas().add(cuenta);
            return true;
        }
        return false;
    }

    @Override
    public boolean eliminarCuenta(int id, String numCuenta) {
        Cuenta cuenta = obtenerCuenta(id, numCuenta);
        if (cuenta != null){
            listaCuentas.remove(cuenta);
            cuenta.getUsuarioAsociado().getListaCuentas().remove(cuenta);
            return true;
        }
        return false;
    }

    @Override
    public boolean actualizarCuenta(Cuenta cuentaVieja, String idUsuarioViejo, String idUsuarioNuevo, Cuenta nuevaCuenta) {
        for (Cuenta cuenta : listaCuentas){
            if (cuenta.getIdCuenta() == cuentaVieja.getIdCuenta()){
                if ((obtenerCuentaId(nuevaCuenta.getIdCuenta()) == null
                        || nuevaCuenta.getIdCuenta() == cuentaVieja.getIdCuenta()) &&
                        (obtenerCuentaNumCuenta(nuevaCuenta.getNumeroCuenta()) == null
                                || nuevaCuenta.getNumeroCuenta().equals(cuentaVieja.getNumeroCuenta()))){
                    cuenta.setIdCuenta(nuevaCuenta.getIdCuenta());
                    cuenta.setNombreBanco(nuevaCuenta.getNombreBanco());
                    cuenta.setNumeroCuenta(nuevaCuenta.getNumeroCuenta());
                    cambiarUsuarioCuenta(cuenta, nuevaCuenta);
                    cuenta.setTipoCuenta(nuevaCuenta.getTipoCuenta());
                    cuenta.setUsuarioAsociado(nuevaCuenta.getUsuarioAsociado());
                    return true;
                }
            }
        }
        return false;
    }

    private Cuenta obtenerCuentaId(int id){
        for (Cuenta cuenta : listaCuentas){
            if (cuenta.getIdCuenta() == id){
                return cuenta;
            }
        }
        return null;
    }

    public Cuenta obtenerCuentaNumCuenta(String numCuenta){
        for (Cuenta cuenta : listaCuentas){
            if (cuenta.getNumeroCuenta().equalsIgnoreCase(numCuenta)){
                return cuenta;
            }
        }
        return null;
    }

    public boolean verificarCuentaId(int id) {
        for (Cuenta cuenta : listaCuentas){
            if (cuenta.getIdCuenta() == id){
                return true;
            }
        }
        return false;
    }

    public boolean verificarCuentaNumCuenta(String numCuenta) {
        for (Cuenta cuenta : listaCuentas){
            if (cuenta.getNumeroCuenta().equalsIgnoreCase(numCuenta)){
                return true;
            }
        }
        return false;
    }

    private void cambiarUsuarioCuenta(Cuenta cuenta, Cuenta nuevaCuenta) {
        Usuario usuarioViejo = cuenta.getUsuarioAsociado();
        Usuario usuarioNuevo = nuevaCuenta.getUsuarioAsociado();
        if (!usuarioViejo.getIdUsuario().equals(usuarioNuevo.getIdUsuario())){
            usuarioViejo.getListaCuentas().remove(cuenta);
            usuarioNuevo.getListaCuentas().add(cuenta);
        }
    }

    @Override
    public Cuenta obtenerCuenta(int id, String numCuenta) {
        for (Cuenta cuenta : listaCuentas){
            if (cuenta.getIdCuenta() == id || cuenta.getNumeroCuenta().equalsIgnoreCase(numCuenta)){
                return cuenta;
            }
        }
        return null;
    }

    @Override
    public boolean agregarCategoria(Categoria categoria) {
        if (obtenerCategoria(categoria.getIdCategoria()) == null){
            listaCategorias.add(categoria);
            categoria.getUsuarioAsociado().getListaCategorias().add(categoria);
            return true;
        }
        return false;
    }

    @Override
    public boolean actualizarCategoria(int idCategoria, Categoria nuevaCategoria) {
        for (Categoria categoria : listaCategorias){
            if (categoria.getIdCategoria() == idCategoria){
                if (obtenerCategoria(nuevaCategoria.getIdCategoria()) == null ||
                        nuevaCategoria.getIdCategoria() == idCategoria){
                    categoria.setIdCategoria(nuevaCategoria.getIdCategoria());
                    categoria.setNombre(nuevaCategoria.getNombre());
                    categoria.setDescripcionOpcional(nuevaCategoria.getDescripcionOpcional());
                    categoria.setPresupuestoAsignado(nuevaCategoria.getPresupuestoAsignado());
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean eliminarCategoria(int idCategoria) {
        Categoria categoria = obtenerCategoria(idCategoria);
        if (categoria != null){
            listaCategorias.remove(categoria);
            categoria.getUsuarioAsociado().getListaCategorias().remove(categoria);
            listaPresupuestos.remove(categoria.getPresupuestoAsignado());
            categoria.getUsuarioAsociado().getListaPresupuestos().remove(categoria.getPresupuestoAsignado());
            return true;
        }
        return false;
    }

    public Categoria obtenerCategoriaPorNombre(String nombreCategoria) {
        if (nombreCategoria != null){
            for (Categoria categoria : listaCategorias){
                if (categoria.getNombre().equalsIgnoreCase(nombreCategoria)){
                    return categoria;
                }
            }
        }
        return null;
    }

    @Override
    public Categoria obtenerCategoria(int idCategoria) {
        for (Categoria categoria : listaCategorias){
            if (categoria.getIdCategoria() == idCategoria){
                return categoria;
            }
        }
        return null;
    }

    @Override
    public boolean agregarPresupuesto(Presupuesto presupuesto) {
        if (obtenerPresupuesto(presupuesto.getIdPresupuesto()) == null){
            listaPresupuestos.add(presupuesto);
            return true;
        }
        return false;
    }

    @Override
    public boolean eliminarPresupuesto(int idPresupuesto) {
        Presupuesto presupuesto = obtenerPresupuesto(idPresupuesto);
        if (presupuesto != null){
            desasociarPresupuestoModelo(presupuesto);
            return true;
        }
        return false;
    }

    private void desasociarPresupuestoModelo(Presupuesto presupuesto) {
        presupuesto.getUsuarioAsociado().getListaPresupuestos().remove(presupuesto);
        presupuesto.getCategoriaPresupuesto().setPresupuestoAsignado(null);
        listaPresupuestos.remove(presupuesto);
    }

    @Override
    public boolean actualizarPresupuesto(int id, Presupuesto nuevoPresupuesto) {
        for (Presupuesto presupuesto : listaPresupuestos){
            if (presupuesto.getIdPresupuesto() == id){
                if (obtenerPresupuesto(nuevoPresupuesto.getIdPresupuesto()) == null || presupuesto.getIdPresupuesto() == id){
                    presupuesto.setIdPresupuesto(id);
                    presupuesto.setNombre(nuevoPresupuesto.getNombre());
                    presupuesto.setMontoTotalAsignado(nuevoPresupuesto.getMontoTotalAsignado());
                    presupuesto.setMontoGastado(nuevoPresupuesto.getMontoGastado());
                    presupuesto.setUsuarioAsociado(nuevoPresupuesto.getUsuarioAsociado());
                    presupuesto.setCategoriaPresupuesto(nuevoPresupuesto.getCategoriaPresupuesto());
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Presupuesto obtenerPresupuesto(int id) {
        for (Presupuesto presupuesto : listaPresupuestos){
            if (presupuesto.getIdPresupuesto() == id){
                return presupuesto;
            }
        }
        return null;
    }

    @Override
    public boolean agregarTransaccion(Transaccion transaccion) {
        if (obtenerTransaccion(transaccion.getIdTransaccion()) == null && cuentasExisten(transaccion)){
            listaTransacciones.add(transaccion);
            return true;
        }
        return false;
    }

    private boolean cuentasExisten(Transaccion transaccion) {
        if (transaccion.getCuentaOrigen() == null) {
            return false;
        }
        Cuenta cuentaOrigen = transaccion.getCuentaOrigen();
        if (transaccion.getTipoTransaccion().equals(TipoTransaccion.TRANSFERENCIA)){
            if (transaccion.getCuentaDestino() != null) {
                return transaccion.getCuentaDestino() != null;
            }
        }
        return true;
    }

    public boolean cuentasExisten(String numCuentaOrigen, String numCuentaDestino) {
        if (numCuentaOrigen != null && numCuentaDestino != null){
            Cuenta cuentaOrigen = obtenerCuentaNumCuenta(numCuentaOrigen);
            Cuenta cuentaDestino = obtenerCuentaNumCuenta(numCuentaDestino);
            return cuentaOrigen !=null && cuentaDestino != null;
        }
        return false;
    }

    @Override
    public boolean actualizarTransaccion(int idTransaccion, Transaccion nuevaTransaccion) {
        for (int i = 0; i < listaTransacciones.size(); i++) {
            if (listaTransacciones.get(i).getIdTransaccion() == idTransaccion){
                if (obtenerTransaccion(nuevaTransaccion.getIdTransaccion()) == null || nuevaTransaccion.getIdTransaccion() == idTransaccion){
                    intercambiarInstanciasTransaccionBilletera(idTransaccion, nuevaTransaccion);
                    intercambiarInstanciasTransaccionUsuario(listaTransacciones.get(i), nuevaTransaccion);
                    intercambiarInstanciasTransaccionCategoria(listaTransacciones.get(i), nuevaTransaccion);
                    intercambiarInstanciasTransaccionCuentaOrigen(listaTransacciones.get(i), nuevaTransaccion);
                    intercambiarInstanciasTransaccionCuentaDestino(listaTransacciones.get(i), nuevaTransaccion);
                    return true;
                }
            }
        }
        return false;
    }

    private void intercambiarInstanciasTransaccionCuentaOrigen(Transaccion viejaTransaccion, Transaccion nuevaTransaccion) {
        LinkedList<Transaccion> listaTransacciones = viejaTransaccion.getCuentaOrigen()
                .getListaTransacciones();
        for (int i = 0; i < listaTransacciones.size(); i++) {
            if (listaTransacciones.get(i).getIdTransaccion() == viejaTransaccion.getIdTransaccion()){
                listaTransacciones.set(i, nuevaTransaccion);
            }
        }
    }

    private void intercambiarInstanciasTransaccionCuentaDestino(Transaccion viejaTransaccion, Transaccion nuevaTransaccion) {
        if (viejaTransaccion.getCuentaDestino() == null){
            return;
        }
        LinkedList<Transaccion> listaTransacciones = viejaTransaccion.getCuentaDestino().getListaTransacciones();
        for (int i = 0; i < listaTransacciones.size(); i++) {
            if (listaTransacciones.get(i).getIdTransaccion() == viejaTransaccion.getIdTransaccion()){
                listaTransacciones.set(i, nuevaTransaccion);
            }
        }
    }

    private void intercambiarInstanciasTransaccionCategoria(Transaccion viejaTransaccion, Transaccion nuevaTransaccion) {
        LinkedList<Transaccion> listaTransacciones = viejaTransaccion.getCategoriaTransaccion()
                .getListaTransacciones();
        for (int i = 0; i < listaTransacciones.size(); i++) {
            if (listaTransacciones.get(i).getIdTransaccion() == viejaTransaccion.getIdTransaccion()){
                listaTransacciones.set(i, nuevaTransaccion);
            }
        }
    }

    private void intercambiarInstanciasTransaccionUsuario(Transaccion viejaTransaccion, Transaccion nuevaTransaccion) {
        LinkedList<Transaccion> listaTransacciones = viejaTransaccion.getUsuarioAsociado()
                .getListaTransacciones();
        for (int i = 0; i < listaTransacciones.size(); i++) {
            if (listaTransacciones.get(i).getIdTransaccion() == viejaTransaccion.getIdTransaccion()){
                listaTransacciones.set(i, nuevaTransaccion);
            }
        }
    }

    private void intercambiarInstanciasTransaccionBilletera(int idTransaccion, Transaccion nuevaTransaccion) {
        for (int i = 0; i < listaTransacciones.size(); i++) {
            if (listaTransacciones.get(i).getIdTransaccion() == idTransaccion){
                listaTransacciones.set(i, nuevaTransaccion);
                break;
            }
        }
    }

    @Override
    public Transaccion obtenerTransaccion(int idTransaccion) {
        for (Transaccion transaccion : listaTransacciones){
            if (transaccion.getIdTransaccion() == idTransaccion){
                return transaccion;
            }
        }
        return null;
    }

    public boolean verificarClaveAdmin(int clave) {
        if (administrador.getClave() == clave){
            return true;
        }
        return false;
    }

    public boolean verificarCredencialesUsuario(String id, int clave) {
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getIdUsuario().equals(id) && usuario.getClave() == clave){
                return true;
            }
        }
        return false;
    }

    public LinkedList<String> obtenerUsuariosId() {
        LinkedList<String> listaUsuariosId = new LinkedList<>();
        for (Usuario usuario : listaUsuarios) {
            listaUsuariosId.add(usuario.getIdUsuario());
        }
        return listaUsuariosId;
    }

    public void modificarSaldoTotalCuenta(double dinero, int idCuentaDestino) {
        Cuenta cuenta = obtenerCuentaId(idCuentaDestino);
        if (cuenta != null) {
            cuenta.modificarSaldoTotal(dinero);
        }
    }

    public LinkedList<String> obtenerCategoriasNombresDeUsuario(String idUsuario) {
        Usuario usuario = obtenerUsuario(idUsuario);
        LinkedList<String> listaCategoriasNombres = new LinkedList<>();
        if (usuario != null) {
            for (Categoria categoria : usuario.getListaCategorias()){
                listaCategoriasNombres.add(categoria.getNombre());
            }
        }
        return listaCategoriasNombres;
    }

    public LinkedList<String> obtenerNumCuentasUsuario(String idUsuario) {
        Usuario usuario = obtenerUsuario(idUsuario);
        LinkedList<String> listaNumCuentasUsuario = new LinkedList<>();
        if (usuario != null) {
            for (Cuenta cuenta : usuario.getListaCuentas()){
                listaNumCuentasUsuario.add(cuenta.getNumeroCuenta());
            }
        }
        return listaNumCuentasUsuario;
    }

    public int obtenerNuevoIdTransaccion() {
        return listaTransacciones.size()+1;
    }
}