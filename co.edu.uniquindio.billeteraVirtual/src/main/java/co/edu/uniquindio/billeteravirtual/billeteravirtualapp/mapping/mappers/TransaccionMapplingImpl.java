package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.mappers;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.TransaccionDto;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.*;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.service.ITransaccionMapping;

import java.util.LinkedList;

public class TransaccionMapplingImpl implements ITransaccionMapping {

    @Override
    public LinkedList<TransaccionDto> getTransaccionDto(LinkedList<Transaccion> listaTransaccion) {
        if (listaTransaccion == null) {
            return null;
        }
        LinkedList<TransaccionDto> listaTransaccionesDto = new LinkedList<>();
        for (Transaccion transaccion : listaTransaccion) {
            listaTransaccionesDto.add(transaccionToTransaccionDto(transaccion));
        }
        return listaTransaccionesDto;
    }

    @Override
    public TransaccionDto transaccionToTransaccionDto(Transaccion transaccion) {
        return new TransaccionDto(
                transaccion.getIdTransaccion(),
                transaccion.getFecha(),
                transaccion.getMonto(),
                transaccion.getDescripcionOpcional(),
                transaccion.getUsuarioAsociado().getIdUsuario(),
                transaccion.getCuentaOrigen().getNumeroCuenta(),
                mapNumeroCuenta(transaccion.getCuentaDestino()),
                transaccion.getCategoriaTransaccion().getNombre());
    }

    @Override
    public Transaccion transaccionDtoToTransaccion(TransaccionDto transaccionDto,
                                                   BilleteraVirtual billeteraVirtual, Usuario usuarioAsociado,
                                                   Categoria categoria, Cuenta cuentaOrigen,
                                                   Cuenta cuentaDestino) {
        return Transaccion.builder()
                .billeteraVirtual(billeteraVirtual)
                .idTransaccion(transaccionDto.idTransaccion())
                .fecha(transaccionDto.fecha())
                .monto(transaccionDto.monto())
                .descripcionOpcional(transaccionDto.descripcion())
                .usuarioAsociado(usuarioAsociado)
                .categoriaTransaccion(categoria)
                .cuentaOrigen(cuentaOrigen)
                .cuentaDestino(cuentaDestino)
                .build();
    }

    @Override
    public String mapNumeroCuenta(Cuenta cuenta) {
        return (cuenta != null) ? cuenta.getNumeroCuenta() : null;
    }
}