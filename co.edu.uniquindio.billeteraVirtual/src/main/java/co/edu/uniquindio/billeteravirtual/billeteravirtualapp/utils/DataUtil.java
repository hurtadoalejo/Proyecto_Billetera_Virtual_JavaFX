package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.utils;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.Administrador;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.BilleteraVirtual;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.Usuario;

public class DataUtil {

    public static BilleteraVirtual inicializarDatos(){
        BilleteraVirtual billeteraVirtual = new BilleteraVirtual("Nuqui");
        Usuario usuario = new Usuario("Alejandro Hurtado", "1092850037", "alejo@gmail.com", "3161971519", "Cra 6 #11-13", 24, billeteraVirtual);
        Usuario usuario2 = new Usuario("Veronica Ibarra", "1092850038", "alejo@gmail.com", "3161971519", "Cra 6 #11-13", 25, billeteraVirtual);
        billeteraVirtual.getListaUsuarios().add(usuario);
        billeteraVirtual.getListaUsuarios().add(usuario2);
        return billeteraVirtual;
    }
}
