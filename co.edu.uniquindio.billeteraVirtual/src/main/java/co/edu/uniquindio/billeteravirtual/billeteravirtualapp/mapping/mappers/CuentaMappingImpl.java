package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.mappers;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.CuentaDto;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.Cuenta;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.Usuario;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.service.ICuentaMapping;

import java.util.LinkedList;

public class CuentaMappingImpl implements ICuentaMapping {
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

    @Override
    public CuentaDto cuentaToCuentaDto(Cuenta cuenta) {
        return new CuentaDto(cuenta.getIdCuenta(), cuenta.getNombreBanco(), cuenta.getNumeroCuenta(),
                cuenta.getUsuarioAsociado().getIdUsuario(), cuenta.getTipoCuenta());
    }

    @Override
    public Cuenta cuentaDtoToCuenta(CuentaDto cuentaDto, Usuario usuario) {
        Cuenta cuenta = new Cuenta();
        cuenta.setIdCuenta(cuentaDto.idCuenta());
        cuenta.setNombreBanco(cuentaDto.nombreBanco());
        cuenta.setNumeroCuenta(cuentaDto.numCuenta());
        cuenta.setTipoCuenta(cuentaDto.tipoCuenta());
        cuenta.setUsuarioAsociado(usuario);
        return cuenta;
    }
}
