package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.factory;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.UsuarioDto;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.mappers.UsuarioMappingImpl;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.BilleteraVirtual;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.Usuario;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.service.IModelFactoryService;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.service.IUsuarioMapping;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.utils.DataUtil;

import java.util.LinkedList;

public class ModelFactory implements IModelFactoryService {
    private static ModelFactory modelFactory;
    private BilleteraVirtual billeteraVirtual;
    private IUsuarioMapping usuarioMapper;

    public static ModelFactory getInstance(){
        if (modelFactory == null){
            modelFactory = new ModelFactory();
        }
        return modelFactory;
    }

    private ModelFactory(){
        usuarioMapper = new UsuarioMappingImpl();
        billeteraVirtual = DataUtil.inicializarDatos();
    }

    public boolean verificarClaveAdmin(int clave) {
        return billeteraVirtual.verificarClaveAdmin(clave);
    }

    public Usuario verificarCredencialesUsuario(String id, int clave) {
        return billeteraVirtual.verificarCredencialesUsuario(id, clave);
    }

    @Override
    public LinkedList<UsuarioDto> obtenerUsuarios() {
        return usuarioMapper.getUsuariosDto(billeteraVirtual.getListaUsuarios());
    }
}