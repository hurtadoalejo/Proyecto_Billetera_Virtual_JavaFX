package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.utils;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.*;

public class DataUtil {

    /**
     * Metodo para inicializar datos de la billetera virtual
     * @return
     */
    public static BilleteraVirtual inicializarDatos(){
        BilleteraVirtual billeteraVirtual = new BilleteraVirtual("Nuqui");
        Usuario usuario = new Usuario("Alejandro Hurtado", "1092850037", "alejo@gmail.com", "3161971519", "Cra 6 #11-13", 24, billeteraVirtual);
        Usuario usuario2 = new Usuario("Veronica Ibarra", "1092850038", "alejo@gmail.com", "3161971519", "Cra 6 #11-13", 25, billeteraVirtual);
        Presupuesto presupuesto1 = new Presupuesto(1, "Comidita", 10000, usuario, TipoPresupuesto.COMIDA, billeteraVirtual);
        Presupuesto presupuesto2 = new Presupuesto(2, "Transporte", 15000, usuario, TipoPresupuesto.TRANSPORTE, billeteraVirtual);
        Cuenta cuenta = new Cuenta(24, "24", "24", usuario, TipoCuenta.AHORRO, billeteraVirtual, presupuesto1);
        Cuenta cuenta2 = new Cuenta(25, "25", "25", usuario, TipoCuenta.AHORRO, billeteraVirtual, presupuesto2);
        billeteraVirtual.agregarUsuario(usuario);
        billeteraVirtual.agregarUsuario(usuario2);
        usuario.agregarPresupuesto(presupuesto1);
        usuario.agregarPresupuesto(presupuesto2);
        usuario.agregarCuenta(cuenta);
        usuario.agregarCuenta(cuenta2);
        Categoria categoria1 = new Categoria(1, "Deposito", "Categoria para depositos", usuario, billeteraVirtual);
        Categoria categoria2 = new Categoria(2, "Retiro", "Categoria para retiros", usuario, billeteraVirtual);
        Categoria categoria3 = new Categoria(3, "Transferencias", "Categoria para transferencias", usuario, billeteraVirtual);
        usuario.agregarCategoria(categoria1);
        usuario.agregarCategoria(categoria2);
        usuario.agregarCategoria(categoria3);
        return billeteraVirtual;
    }
}
