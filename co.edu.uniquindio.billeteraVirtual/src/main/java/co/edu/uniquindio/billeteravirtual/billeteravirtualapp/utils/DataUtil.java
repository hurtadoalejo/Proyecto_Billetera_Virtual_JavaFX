package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.utils;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.*;

import java.time.LocalDate;

public class DataUtil {

    public static BilleteraVirtual inicializarDatos(){
        BilleteraVirtual billeteraVirtual = new BilleteraVirtual("Nuqui");
        Usuario usuario1 = new Usuario("Alejandro Hurtado", "1092850037", "alejo@gmail.com", "3161971519", "Cra 6 #11-13", 24, billeteraVirtual);
        Usuario usuario2 = new Usuario("Veronica Ibarra", "1092850038", "alejo@gmail.com", "3161971519", "Cra 6 #11-13", 25, billeteraVirtual);
        Categoria categoria1 = new Categoria(1, "Comida semanal", "Categoria semanal", usuario1, null, billeteraVirtual);
        Categoria categoria2 = new Categoria(2, "Transporte semanal", "Categoria semanal", usuario1, null, billeteraVirtual);
        Categoria categoria3 = new Categoria(3, "Rumba semanal", "Categoria semanal", usuario1, null, billeteraVirtual);
        Presupuesto presupuesto1 = new Presupuesto(1, "Comidita", 500000, usuario1, categoria1, billeteraVirtual);
        Presupuesto presupuesto2 = new Presupuesto(2, "Transporte", 300000, usuario1, categoria2, billeteraVirtual);
        Presupuesto presupuesto3 = new Presupuesto(3, "Rumba", 150000, usuario1, categoria3, billeteraVirtual);
        Cuenta cuenta = new Cuenta(1, "Davivienda", "13657348", usuario1, TipoCuenta.AHORRO, billeteraVirtual, presupuesto1);
        Cuenta cuenta2 = new Cuenta(2, "Bancolombia", "25078922", usuario1, TipoCuenta.AHORRO, billeteraVirtual, presupuesto2);
        Cuenta cuenta3 = new Cuenta(3, "NuBank", "7879250", usuario1, TipoCuenta.CORRIENTE, billeteraVirtual, presupuesto3);
        billeteraVirtual.agregarUsuario(usuario1);
        billeteraVirtual.agregarUsuario(usuario2);
        usuario1.agregarCategoria(categoria1);
        usuario1.agregarCategoria(categoria2);
        usuario1.agregarCategoria(categoria3);
        usuario1.agregarPresupuesto(presupuesto1);
        usuario1.agregarPresupuesto(presupuesto2);
        usuario1.agregarPresupuesto(presupuesto3);
        usuario1.agregarCuenta(cuenta);
        usuario1.agregarCuenta(cuenta2);
        usuario1.agregarCuenta(cuenta3);

        Transaccion transaccion1 = new Transaccion(1, LocalDate.of(2025,5,1), 700000, "Trabajo", usuario1, TipoTransaccion.DEPOSITO, cuenta, null, billeteraVirtual);
        Transaccion transaccion2 = new Transaccion(2, LocalDate.of(2025,5,2), 25000, "Tarea", usuario1, TipoTransaccion.DEPOSITO, cuenta, null, billeteraVirtual);
        Transaccion transaccion3 = new Transaccion(3, LocalDate.of(2025,5,3), 300000, "Consignación papás", usuario1, TipoTransaccion.DEPOSITO, cuenta, null, billeteraVirtual);
        Transaccion transaccion4 = new Transaccion(4, LocalDate.of(2025,5,4), 150000, "Reembolso zapatos", usuario1, TipoTransaccion.DEPOSITO, cuenta2, null, billeteraVirtual);
        Transaccion transaccion5 = new Transaccion(5, LocalDate.of(2025,5,5), 20000, "Apuesta ganada", usuario1, TipoTransaccion.DEPOSITO, cuenta2, null, billeteraVirtual);
        Transaccion transaccion6 = new Transaccion(6, LocalDate.of(2025,5,6), 300000, "Consignación papás", usuario1, TipoTransaccion.DEPOSITO, cuenta2, null, billeteraVirtual);
        Transaccion transaccion7 = new Transaccion(7, LocalDate.of(2025,5,7), 76000, "Pago deuda Camilo", usuario1, TipoTransaccion.DEPOSITO, cuenta2, null, billeteraVirtual);
        Transaccion transaccion8 = new Transaccion(8, LocalDate.of(2025,5,8), 110000, "Pago deuda Sara", usuario1, TipoTransaccion.DEPOSITO, cuenta3, null, billeteraVirtual);
        usuario1.agregarTransaccion(transaccion1);
        usuario1.agregarTransaccion(transaccion2);
        usuario1.agregarTransaccion(transaccion3);
        usuario1.agregarTransaccion(transaccion4);
        usuario1.agregarTransaccion(transaccion5);
        usuario1.agregarTransaccion(transaccion6);
        usuario1.agregarTransaccion(transaccion7);
        usuario1.agregarTransaccion(transaccion8);
        Transaccion transaccion9 = new Transaccion(9, LocalDate.of(2025,5,8), 15000, "Primer transferencia", usuario1, TipoTransaccion.TRANSFERENCIA, cuenta, cuenta2, billeteraVirtual);
        Transaccion transaccion10 = new Transaccion(10, LocalDate.of(2025,5,9), 30000, "Primer retiro", usuario1, TipoTransaccion.RETIRO, cuenta2, null, billeteraVirtual);
        usuario1.agregarTransaccion(transaccion9);
        usuario1.agregarTransaccion(transaccion10);
        return billeteraVirtual;
    }
}
