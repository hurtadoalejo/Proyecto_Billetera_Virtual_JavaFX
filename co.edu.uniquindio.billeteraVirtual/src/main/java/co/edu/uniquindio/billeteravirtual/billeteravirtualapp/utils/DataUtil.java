package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.utils;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.*;

import java.time.LocalDate;

public class DataUtil {

    public static BilleteraVirtual inicializarDatos(){
        BilleteraVirtual billeteraVirtual = new BilleteraVirtual("Nuqui");
        Usuario usuario1 = new Usuario("Alejandro Hurtado", "1092850037", "alejo@gmail.com", "3161971519", "Cra 6 #11-13", 24, billeteraVirtual);
        Usuario usuario2 = new Usuario("Veronica Ibarra", "1092850038", "alejo@gmail.com", "3161971519", "Cra 6 #11-13", 25, billeteraVirtual);
        Presupuesto presupuesto1 = new Presupuesto(1, "Comidita", 10000, usuario1, TipoPresupuesto.COMIDA, billeteraVirtual);
        Presupuesto presupuesto2 = new Presupuesto(2, "Transporte", 15000, usuario1, TipoPresupuesto.TRANSPORTE, billeteraVirtual);
        Cuenta cuenta = new Cuenta(24, "24", "24", usuario1, TipoCuenta.AHORRO, billeteraVirtual, presupuesto1);
        Cuenta cuenta2 = new Cuenta(25, "25", "25", usuario1, TipoCuenta.AHORRO, billeteraVirtual, presupuesto2);
        billeteraVirtual.agregarUsuario(usuario1);
        billeteraVirtual.agregarUsuario(usuario2);
        usuario1.agregarPresupuesto(presupuesto1);
        usuario1.agregarPresupuesto(presupuesto2);
        usuario1.agregarCuenta(cuenta);
        usuario1.agregarCuenta(cuenta2);
        Categoria categoria1 = new Categoria(1, "Deposito", "Categoria para depositos", usuario1, billeteraVirtual);
        Categoria categoria2 = new Categoria(2, "Retiro", "Categoria para retiros", usuario1, billeteraVirtual);
        Categoria categoria3 = new Categoria(3, "Transferencia", "Categoria para transferencias", usuario1, billeteraVirtual);
        usuario1.agregarCategoria(categoria1);
        usuario1.agregarCategoria(categoria2);
        usuario1.agregarCategoria(categoria3);
        Transaccion transaccion1 = new Transaccion(1, LocalDate.of(2025,5,2), 15000, "Consignación", usuario1, categoria1, cuenta, null, billeteraVirtual);
        usuario1.agregarTransaccion(transaccion1);
        return billeteraVirtual;
    }
}
