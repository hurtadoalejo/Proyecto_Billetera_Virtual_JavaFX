package co.edu.uniquindio.billeteravirtual.utils;

import co.edu.uniquindio.billeteravirtual.model.Administrador;
import co.edu.uniquindio.billeteravirtual.model.BilleteraVirtual;

public class DataUtil {

    public static BilleteraVirtual inicializarDatos(){
        BilleteraVirtual billeteraVirtual = new BilleteraVirtual("Nuqui");
        billeteraVirtual.setAdministrador(new Administrador(billeteraVirtual, 2911));


        return billeteraVirtual;
    }
}
