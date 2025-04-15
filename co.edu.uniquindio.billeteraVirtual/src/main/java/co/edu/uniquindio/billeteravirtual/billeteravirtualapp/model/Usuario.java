package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.service.ICrudTransaccion;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.service.IGestionDinero;

import java.util.LinkedList;

public class Usuario implements IGestionDinero, ICrudTransaccion {
    private BilleteraVirtual billeteraVirtual;
    private String nombreCompleto, idUsuario, correoElectronico, numeroTelefono, direccion;
    private int clave;
    private double saldoTotal;
    private LinkedList<Cuenta> listaCuentas;
    private LinkedList<Presupuesto> listaPresupuestos;
    private LinkedList<Transaccion> listaTransacciones;
    private LinkedList<Categoria> listaCategorias;

    public Usuario(){
        listaCuentas = new LinkedList<>();
        listaPresupuestos = new LinkedList<>();
        listaTransacciones = new LinkedList<>();
        listaCategorias = new LinkedList<>();
    }

    public Usuario(String nombreCompleto, String idUsuario, String correoElectronico, String numeroTelefono, String direccion, int clave, BilleteraVirtual billeteraVirtual) {
        this.nombreCompleto = nombreCompleto;
        this.idUsuario = idUsuario;
        this.correoElectronico = correoElectronico;
        this.numeroTelefono = numeroTelefono;
        this.direccion = direccion;
        this.clave = clave;
        this.billeteraVirtual = billeteraVirtual;
        listaCuentas = new LinkedList<>();
        listaPresupuestos = new LinkedList<>();
        listaTransacciones = new LinkedList<>();
        listaCategorias = new LinkedList<>();
    }

    public BilleteraVirtual getBilleteraVirtual() {
        return billeteraVirtual;
    }

    public void setBilleteraVirtual(BilleteraVirtual billeteraVirtual) {
        this.billeteraVirtual = billeteraVirtual;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getClave() {
        return clave;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }

    public double getSaldoTotal() {
        return saldoTotal;
    }

    public void setSaldoTotal(double saldoTotal) {
        this.saldoTotal = saldoTotal;
    }

    public LinkedList<Cuenta> getListaCuentas() {
        return listaCuentas;
    }

    public void setListaCuentas(LinkedList<Cuenta> listaCuentas) {
        this.listaCuentas = listaCuentas;
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

    public LinkedList<Categoria> getListaCategorias() {
        return listaCategorias;
    }

    public void setListaCategorias(LinkedList<Categoria> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    private Cuenta obtenerCuenta(int idCuenta) {
        for (Cuenta cuenta : listaCuentas) {
            if (cuenta.getIdCuenta() == idCuenta) {
                return cuenta;
            }
        }
        return null;
    }

    @Override
    public boolean agregarTransaccion(Transaccion transaccion) {
        if (esTransaccionPosible(transaccion) && saldoCuentaEsSuficiente(transaccion)) {
            if (billeteraVirtual.agregarTransaccion(transaccion)) {
                if (transaccion.getTipoTransaccion().equals(TipoTransaccion.DEPOSITO)){
                    agregarDinero(transaccion.getMonto(), transaccion.getCuentaOrigen().getIdCuenta());
                }
                else if (transaccion.getTipoTransaccion().equals(TipoTransaccion.TRANSFERENCIA)){
                    transferirDinero(transaccion.getMonto(), transaccion.getCuentaOrigen().getIdCuenta(),
                            transaccion.getCuentaDestino().getIdCuenta());
                }
                else if (transaccion.getTipoTransaccion().equals(TipoTransaccion.RETIRO)){
                    retirarDinero(transaccion.getMonto(), transaccion.getCuentaOrigen().getIdCuenta());
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean actualizarTransaccion(int idTransaccion, Transaccion nuevaTransaccion) {
        return false;
    }

    @Override
    public Transaccion obtenerTransaccion(int idTransaccion) {
        return null;
    }

    @Override
    public boolean agregarDinero(double dinero, int idCuenta) {
        Cuenta cuenta = obtenerCuenta(idCuenta);
        if (cuenta != null) {
            cuenta.modificarSaldoTotal(dinero);
            modificarSaldoTotal(dinero);
            return true;
        }
        return false;
    }

    @Override
    public boolean retirarDinero(double dinero, int idCuenta) {
        Cuenta cuenta = obtenerCuenta(idCuenta);
        if (cuenta != null) {
            cuenta.modificarSaldoTotal(dinero*-1);
            modificarSaldoTotal(dinero*-1);
            return true;
        }
        return false;
    }

    @Override
    public boolean transferirDinero(double dinero, int idCuentaOrigen, int idCuentaDestino) {
        Cuenta cuenta = obtenerCuenta(idCuentaOrigen);
        if (cuenta != null) {
            cuenta.modificarSaldoTotal(dinero*-1);
            modificarSaldoTotal(dinero*-1);
            billeteraVirtual.modificarSaldoTotalCuenta(dinero, idCuentaDestino);
            return true;
        }
        return false;
    }

    public boolean agregarCuenta(Cuenta cuenta) {
        return billeteraVirtual.agregarCuenta(cuenta, idUsuario);
    }

    public boolean actualizarCuenta(Cuenta cuentaVieja, Cuenta nuevaCuenta) {
        return billeteraVirtual.actualizarCuenta(cuentaVieja, this.idUsuario,
                this.idUsuario, nuevaCuenta);
    }

    public boolean eliminarCuenta(int idCuenta, String numCuenta) {
        return billeteraVirtual.eliminarCuenta(idCuenta, numCuenta);
    }

    private void modificarSaldoTotal(double saldoDado) {
        saldoTotal = saldoTotal+saldoDado;
    }

    private boolean esTransaccionPosible(Transaccion transaccion) {
        Categoria categoria = transaccion.getCategoriaTransaccion();
        if (categoria != null) {
            Presupuesto presupuesto = categoria.getPresupuesto();
            if (presupuesto != null) {
                return transaccionPasaPrespuesto(transaccion.getMonto(), presupuesto);
            }
        }
        return true;
    }

    private boolean transaccionPasaPrespuesto(Double montoTransaccion, Presupuesto presupuesto) {
        double montoGastadoFuturo = montoTransaccion + presupuesto.getMontoGastado();
        return montoGastadoFuturo <= presupuesto.getMontoTotalAsignado();
    }

    public boolean saldoCuentaEsSuficiente(Transaccion transaccion) {
        if (transaccion.getTipoTransaccion().equals(TipoTransaccion.DEPOSITO)) {
            return true;
        }
        Cuenta cuenta = transaccion.getCuentaOrigen();
        return cuenta.getSaldo() >= transaccion.getMonto();
    }
}