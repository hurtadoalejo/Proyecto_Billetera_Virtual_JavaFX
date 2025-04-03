package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model;

import java.util.LinkedList;

public class Categoria {
    private BilleteraVirtual billeteraVirtual;
    private int idCategoria;
    private String nombre, descripcionOpcional;
    private LinkedList<Transaccion> listaTransacciones;
    private LinkedList<Presupuesto> listaPresupuestos;

    public Categoria() {}

    public Categoria(int idCategoria, String nombre, String descripcionOpcional, BilleteraVirtual billeteraVirtual) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;


        this.descripcionOpcional = descripcionOpcional;
        this.billeteraVirtual = billeteraVirtual;
    }

    public BilleteraVirtual getBilleteraVirtual() {
        return billeteraVirtual;
    }

    public void setBilleteraVirtual(BilleteraVirtual billeteraVirtual) {
        this.billeteraVirtual = billeteraVirtual;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcionOpcional() {
        return descripcionOpcional;
    }

    public void setDescripcionOpcional(String descripcionOpcional) {
        this.descripcionOpcional = descripcionOpcional;
    }

    public LinkedList<Transaccion> getListaTransacciones() {
        return listaTransacciones;
    }

    public void setListaTransacciones(LinkedList<Transaccion> listaTransacciones) {
        this.listaTransacciones = listaTransacciones;
    }

    public LinkedList<Presupuesto> getListaPresupuestos() {
        return listaPresupuestos;
    }

    public void setListaPresupuestos(LinkedList<Presupuesto> listaPresupuestos) {
        this.listaPresupuestos = listaPresupuestos;
    }
}