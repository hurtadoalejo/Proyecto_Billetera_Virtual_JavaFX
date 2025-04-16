package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.mappers;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.CuentaDto;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.BilleteraVirtual;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.Cuenta;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.Usuario;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.service.ICuentaMapping;

import java.util.LinkedList;

public class CuentaMappingImpl implements ICuentaMapping {

    /**
     * Metodo para convertir una lista de cuentas en una lista de cuentas dto
     * @param listaCuentas Lista de cuentas a convertir
     * @return Lista de cuentas dto
     */
    @Override
    public LinkedList<CuentaDto> getCuentasDto(LinkedList<Cuenta> listaCuentas) {
        if (listaCuentas == null) {
            return null;
        }
        LinkedList<CuentaDto> listaCuentasDto = new LinkedList<>();
        for (Cuenta cuenta : listaCuentas) {
            listaCuentasDto.add(cuentaToCuentaDto(cuenta));
        }
        return listaCuentasDto;
    }

    /**
     * Metodo para convertir una cuenta en cuenta dto
     * @param cuenta Cuenta a convertir
     * @return Cuenta dto
     */
    @Override
    public CuentaDto cuentaToCuentaDto(Cuenta cuenta) {
        return new CuentaDto(cuenta.getIdCuenta(), cuenta.getNombreBanco(), cuenta.getNumeroCuenta(),
                cuenta.getUsuarioAsociado().getIdUsuario(), cuenta.getTipoCuenta(), cuenta.getSaldo());
    }

    /**
     * Metodo para convetir una cuenta dto a cuenta
     * @param cuentaDto Cuenta dto a convertir
     * @param billeteraVirtual Billetera virtual principal
     * @return Cuenta
     */
    @Override
    public Cuenta cuentaDtoToCuenta(CuentaDto cuentaDto, BilleteraVirtual billeteraVirtual, Usuario usuario) {
        Cuenta cuenta = new Cuenta();
        cuenta.setIdCuenta(cuentaDto.idCuenta());
        cuenta.setNombreBanco(cuentaDto.nombreBanco());
        cuenta.setNumeroCuenta(cuentaDto.numCuenta());
        cuenta.setTipoCuenta(cuentaDto.tipoCuenta());
        cuenta.setSaldo(cuentaDto.saldo());
        return cuenta;
    }
}
