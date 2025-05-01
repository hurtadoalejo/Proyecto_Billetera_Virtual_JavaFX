package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model;

public class Presupuesto {
    private BilleteraVirtual billeteraVirtual;
    private int idPresupuesto;
    private String nombre;
    private double montoTotalAsignado, montoGastado;
    private Usuario usuarioAsociado;
    private Cuenta cuentaAsociada;
    private TipoPresupuesto tipoPresupuesto;

    public Presupuesto() {}

    public Presupuesto(int idPresupuesto, String nombre, double montoTotalAsignado, Usuario usuarioAsociado, TipoPresupuesto tipoPresupuesto, BilleteraVirtual billeteraVirtual) {
        this.idPresupuesto = idPresupuesto;
        this.nombre = nombre;
        this.montoTotalAsignado = montoTotalAsignado;
        this.montoGastado = 0;
        this.usuarioAsociado = usuarioAsociado;
        this.tipoPresupuesto = tipoPresupuesto;
        this.billeteraVirtual = billeteraVirtual;
    }

    public BilleteraVirtual getBilleteraVirtual() {
        return billeteraVirtual;
    }

    public void setBilleteraVirtual(BilleteraVirtual billeteraVirtual) {
        this.billeteraVirtual = billeteraVirtual;
    }

    public int getIdPresupuesto() {
        return idPresupuesto;
    }

    public void setIdPresupuesto(int idPresupuesto) {
        this.idPresupuesto = idPresupuesto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getMontoTotalAsignado() {
        return montoTotalAsignado;
    }

    public void setMontoTotalAsignado(double montoTotalAsignado) {
        this.montoTotalAsignado = montoTotalAsignado;
    }

    public double getMontoGastado() {
        return montoGastado;
    }

    public void setMontoGastado(double montoGastado) {
        this.montoGastado = montoGastado;
    }

    public Usuario getUsuarioAsociado() {
        return usuarioAsociado;
    }

    public void setUsuarioAsociado(Usuario usuarioAsociado) {
        this.usuarioAsociado = usuarioAsociado;
    }

    public Cuenta getCuentaAsociada() {
        return cuentaAsociada;
    }

    public void setCuentaAsociada(Cuenta cuentaAsociada) {
        this.cuentaAsociada = cuentaAsociada;
    }

    public TipoPresupuesto getTipoPresupuesto() {
        return tipoPresupuesto;
    }

    public void setTipoPresupuesto(TipoPresupuesto tipoPresupuesto) {
        this.tipoPresupuesto = tipoPresupuesto;
    }

    public void aumentarMontoGastado(double monto) {
        montoGastado += monto;
    }
}