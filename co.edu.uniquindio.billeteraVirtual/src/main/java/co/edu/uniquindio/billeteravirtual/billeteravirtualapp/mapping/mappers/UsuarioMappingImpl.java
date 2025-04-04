package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.mappers;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.UsuarioDto;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.Usuario;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.service.IUsuarioMapping;

import java.util.LinkedList;

public class UsuarioMappingImpl implements IUsuarioMapping {

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
    public Usuario usuarioDtoToUsuario(UsuarioDto usuarioDto) {
        Usuario usuario = new Usuario();
        usuario.setNombreCompleto(usuarioDto.nombreCompleto());
        usuario.setIdUsuario(usuarioDto.idUsuario());
        usuario.setCorreoElectronico(usuarioDto.correoElectronico());
        usuario.setNumeroTelefono(usuarioDto.numeroTelefono());
        usuario.setDireccion(usuarioDto.direccion());
        usuario.setClave(usuarioDto.clave());
        return usuario;
    }
}
