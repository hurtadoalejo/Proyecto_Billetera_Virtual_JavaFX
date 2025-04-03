package co.edu.uniquindio.billeteravirtual.mapping.mappers;

import co.edu.uniquindio.billeteravirtual.mapping.dto.UsuarioDto;
import co.edu.uniquindio.billeteravirtual.model.Usuario;
import co.edu.uniquindio.billeteravirtual.service.IUsuarioMapping;

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
        return null;
    }

    @Override
    public Usuario usuarioDtoToUsuario(UsuarioDto usuarioDto) {
        return null;
    }
}
