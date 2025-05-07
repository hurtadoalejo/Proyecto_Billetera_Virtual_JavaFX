package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.mappers;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.*;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.*;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.service.IBilleteraMapping;

import java.util.LinkedList;

public class BilleteraMappingImpl implements IBilleteraMapping {
    @Override
    public LinkedList<CategoriaDto> getListaCategorias(LinkedList<Categoria> listaCategorias) {
        if (listaCategorias == null) {
            return null;
        }
        LinkedList<CategoriaDto> listaCategoriasDto = new LinkedList<>();
        for (Categoria categoria : listaCategorias) {
            listaCategoriasDto.add(categoriaToCategoriaDto(categoria));
        }
        return listaCategoriasDto;
    }

    @Override
    public CategoriaDto categoriaToCategoriaDto(Categoria categoria) {
        String presupuestoAsociado = categoria.getPresupuestoAsociado() != null
                ? categoria.getPresupuestoAsociado().getNombre() : null;

        return new CategoriaDto(categoria.getIdCategoria(), categoria.getUsuarioAsociado().getIdUsuario(),
                categoria.getNombre(), categoria.getDescripcionOpcional(), presupuestoAsociado);
    }

    @Override
    public Categoria categoriaDtoToCategoria(CategoriaDto categoriaDto, BilleteraVirtual billeteraVirtual,
                                             Usuario usuario) {
        Categoria categoria = new Categoria();
        categoria.setBilleteraVirtual(billeteraVirtual);
        categoria.setUsuarioAsociado(usuario);
        categoria.setNombre(categoriaDto.nombre());
        categoria.setDescripcionOpcional(categoriaDto.descripcion());
        categoria.setIdCategoria(categoriaDto.idCategoria());
        return categoria;
    }

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
                cuenta.getUsuarioAsociado().getIdUsuario(), cuenta.getTipoCuenta(), cuenta.getSaldo(),
                cuenta.getPresupuestoAsociado().getNombre());
    }

    @Override
    public Cuenta cuentaDtoToCuenta(CuentaDto cuentaDto, BilleteraVirtual billeteraVirtual,
                                    Usuario usuario, Presupuesto presupuesto) {
        Cuenta cuenta = new Cuenta();
        cuenta.setIdCuenta(cuentaDto.idCuenta());
        cuenta.setNombreBanco(cuentaDto.nombreBanco());
        cuenta.setNumeroCuenta(cuentaDto.numCuenta());
        cuenta.setTipoCuenta(cuentaDto.tipoCuenta());
        cuenta.setSaldo(cuentaDto.saldo());
        cuenta.setBilleteraVirtual(billeteraVirtual);
        cuenta.setUsuarioAsociado(usuario);
        cuenta.setPresupuestoAsociado(presupuesto);
        return cuenta;
    }

    @Override
    public LinkedList<PresupuestoDto> getPresupuestosDto(LinkedList<Presupuesto> listaPresupuestos) {
        if (listaPresupuestos == null) {
            listaPresupuestos = new LinkedList<>();
        }
        LinkedList<PresupuestoDto> listaPresupuestosDto = new LinkedList<>();
        for (Presupuesto presupuesto : listaPresupuestos) {
            listaPresupuestosDto.add(presupuestoToPresupuestoDto(presupuesto));
        }
        return listaPresupuestosDto;
    }

    @Override
    public PresupuestoDto presupuestoToPresupuestoDto(Presupuesto presupuesto) {
        String numCuenta = presupuesto.getCuentaAsociada() != null
                ? presupuesto.getCuentaAsociada().getNumeroCuenta() : null;
        String categoria = presupuesto.getCategoriaAsociada() != null
                ? presupuesto.getCategoriaAsociada().getNombre() : null;

        return new PresupuestoDto(
                presupuesto.getIdPresupuesto(),
                presupuesto.getNombre(),
                presupuesto.getMontoTotalAsignado(),
                presupuesto.getMontoGastado(),
                presupuesto.getUsuarioAsociado().getIdUsuario(),
                numCuenta,
                categoria);
    }

    @Override
    public Presupuesto presupuestoDtoToPresupuesto(PresupuestoDto presupuestoDto, BilleteraVirtual billeteraVirtual,
                                                   Usuario usuario, Cuenta cuenta, Categoria categoria) {
        Presupuesto presupuesto = new Presupuesto();
        presupuesto.setBilleteraVirtual(billeteraVirtual);
        presupuesto.setIdPresupuesto(presupuestoDto.idPresupuesto());
        presupuesto.setNombre(presupuestoDto.nombre());
        presupuesto.setMontoTotalAsignado(presupuestoDto.montoTotalAsignado());
        presupuesto.setMontoGastado(presupuestoDto.montoGastado());
        presupuesto.setUsuarioAsociado(usuario);
        presupuesto.setCuentaAsociada(cuenta);
        presupuesto.setCategoriaAsociada(categoria);
        return presupuesto;
    }

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
                transaccion.getTipoTransaccion());
    }

    @Override
    public Transaccion transaccionDtoToTransaccion(TransaccionDto transaccionDto,
                                                   BilleteraVirtual billeteraVirtual, Usuario usuarioAsociado,
                                                   Cuenta cuentaOrigen, Cuenta cuentaDestino) {
        return Transaccion.builder()
                .billeteraVirtual(billeteraVirtual)
                .idTransaccion(transaccionDto.idTransaccion())
                .fecha(transaccionDto.fecha())
                .monto(transaccionDto.monto())
                .descripcionOpcional(transaccionDto.descripcion())
                .usuarioAsociado(usuarioAsociado)
                .tipoTransaccion(transaccionDto.tipoTransaccion())
                .cuentaOrigen(cuentaOrigen)
                .cuentaDestino(cuentaDestino)
                .build();
    }

    @Override
    public String mapNumeroCuenta(Cuenta cuenta) {
        return (cuenta != null) ? cuenta.getNumeroCuenta() : null;
    }

    @Override
    public LinkedList<UsuarioDto> getUsuariosDto(LinkedList<Usuario> listaUsuarios) {
        if (listaUsuarios == null){
            return  null;
        }
        LinkedList<UsuarioDto> listaUsuariosDto = new LinkedList<UsuarioDto>();
        for (Usuario usuario : listaUsuarios){
            listaUsuariosDto.add(usuarioToUsuarioDto(usuario));
        }
        return listaUsuariosDto;
    }

    @Override
    public UsuarioDto usuarioToUsuarioDto(Usuario usuario) {
        return new UsuarioDto(usuario.getNombreCompleto(), usuario.getIdUsuario(),
                usuario.getCorreoElectronico(), usuario.getNumeroTelefono(),
                usuario.getDireccion(), usuario.getClave());
    }

    @Override
    public Usuario usuarioDtoToUsuario(UsuarioDto usuarioDto, BilleteraVirtual billeteraVirtual) {
        Usuario usuario = new Usuario();
        usuario.setNombreCompleto(usuarioDto.nombreCompleto());
        usuario.setIdUsuario(usuarioDto.idUsuario());
        usuario.setCorreoElectronico(usuarioDto.correoElectronico());
        usuario.setNumeroTelefono(usuarioDto.numeroTelefono());
        usuario.setDireccion(usuarioDto.direccion());
        usuario.setClave(usuarioDto.clave());
        usuario.setBilleteraVirtual(billeteraVirtual);
        return usuario;
    }
}