package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.service;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.TransaccionDto;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.*;

import java.util.LinkedList;

public interface ITransaccionMapping {
    LinkedList<TransaccionDto> getTransaccionDto(LinkedList<Transaccion> listaTransaccion);
    TransaccionDto transaccionToTransaccionDto(Transaccion transaccion);
    Transaccion transaccionDtoToTransaccion(TransaccionDto transaccionDto,
                                            BilleteraVirtual billeteraVirtual, Usuario usuarioAsociado,
                                            Categoria categoria, Cuenta cuentaOrigen,
                                            Cuenta cuentaDestino);
    String mapNumeroCuenta(Cuenta cuenta);
    String mapNombreCategoria(Categoria categoria);
}