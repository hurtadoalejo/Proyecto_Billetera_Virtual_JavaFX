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
        Cuenta cuenta = new Cuenta(24, "24", "24", usuario, TipoCuenta.AHORRO, billeteraVirtual);
        Cuenta cuenta2 = new Cuenta(25, "25", "25", usuario, TipoCuenta.AHORRO, billeteraVirtual);
        usuario.getListaCuentas().add(cuenta);
        usuario.getListaCuentas().add(cuenta2);
        billeteraVirtual.getListaUsuarios().add(usuario);
        billeteraVirtual.getListaUsuarios().add(usuario2);
        billeteraVirtual.getListaCuentas().add(cuenta);
        billeteraVirtual.getListaCuentas().add(cuenta2);
        return billeteraVirtual;
    }
}
