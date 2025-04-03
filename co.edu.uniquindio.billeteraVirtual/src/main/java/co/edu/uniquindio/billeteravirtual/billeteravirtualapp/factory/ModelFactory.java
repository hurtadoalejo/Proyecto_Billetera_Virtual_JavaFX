package co.edu.uniquindio.billeteravirtual.factory;

import co.edu.uniquindio.billeteravirtual.mapping.dto.UsuarioDto;
import co.edu.uniquindio.billeteravirtual.mapping.mappers.UsuarioMappingImpl;
import co.edu.uniquindio.billeteravirtual.model.BilleteraVirtual;
import co.edu.uniquindio.billeteravirtual.service.IModelFactoryService;
import co.edu.uniquindio.billeteravirtual.service.IUsuarioMapping;
import co.edu.uniquindio.billeteravirtual.utils.DataUtil;

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

    @Override
    public LinkedList<UsuarioDto> obtenerUsuarios() {
        return usuarioMapper.getUsuariosDto(billeteraVirtual.getListaUsuarios());
    }
}