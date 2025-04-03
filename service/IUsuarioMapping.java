package co.edu.uniquindio.billeteravirtual.service;

import co.edu.uniquindio.billeteravirtual.mapping.dto.UsuarioDto;
import co.edu.uniquindio.billeteravirtual.model.Usuario;

import java.util.LinkedList;

public interface IUsuarioMapping {
    LinkedList<UsuarioDto> getUsuariosDto(LinkedList<Usuario> listaUsuarios);
    UsuarioDto usuarioToUsuarioDto(Usuario usuario);
    Usuario usuarioDtoToUsuario(UsuarioDto usuarioDto);
}