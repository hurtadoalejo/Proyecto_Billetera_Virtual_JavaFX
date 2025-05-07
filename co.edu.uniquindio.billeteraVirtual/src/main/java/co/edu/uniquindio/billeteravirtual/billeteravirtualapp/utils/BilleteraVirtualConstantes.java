package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.utils;

import java.security.PublicKey;

public class BilleteraVirtualConstantes {
    public static final String TITULO_INCOMPLETO = "Campos incompletos";
    public static final String HEADER = "Notificacion";
    public static final String BODY_INCOMPLETO = "Los datos del formulario estan incompletos";

    public static final String TITULO_INCORRECTO = "Campos incorrectos";
    public static final String BODY_INCORRECTO = "Los datos del formulario estan incorrectos";

    public static final String TITULO_USUARIO_NO_AGREGADO = "Usuario no agregado";
    public static final String BODY_USUARIO_NO_AGREGADO = "El usuario no se pudo agregar, ya existe";
    public static final String TITULO_USUARIO_NO_CREADO = "Usuario no creado";
    public static final String BODY_USUARIO_NO_CREADO = "El usuario no se pudo crear, el número de identificación ya está en uso";

    public static final String TITULO_USUARIO_AGREGADO = "Usuario agregado";
    public static final String BODY_USUARIO_AGREGADO = "El usuario se agregó exitosamente";
    public static final String TITULO_USUARIO_CREADO = "Usuario creado";
    public static final String BODY_USUARIO_CREADO = "El usuario se creó exitosamente, ya puedes iniciar sesión con tus credenciales";

    public static final String TITULO_USUARIO_ACTUALIZADO = "Usuario actualizado";
    public static final String BODY_USUARIO_ACTUALIZADO = "El usuario se actualizó exitosamente";

    public static final String TITULO_USUARIO_NO_ACTUALIZADO = "Usuario no actualizado";
    public static final String BODY_USUARIO_NO_ACTUALIZADO = "El usuario no se actualizó exitosamente, su numero de ID ya está en uso";

    public static final String TITULO_USUARIO_ELIMINADO = "Usuario eliminado";
    public static final String BODY_USUARIO_ELIMINADO = "El usuario se eliminó exitosamente";

    public static final String TITULO_USUARIO_NO_SELECCIONADO = "Usuario no seleccionado";
    public static final String BODY_USUARIO_NO_SELECCIONADO = "No hay ningun usuario seleccionado";

    public static final String BODY_CONFIRMACION_ELIMINAR_USUARIO = "¿Está seguro de eliminar el usuario?";
    public static final String BODY_CONFIRMACION_ELIMINAR_CUENTA = "¿Está seguro de eliminar la cuenta?";
    public static final String BODY_CONFIRMACION_ELIMINAR_CATEGORIA =
            "¿Está seguro de eliminar la categoria?";
    public static final String BODY_CONFIRMACION_ELIMINAR_PRESUPUESTO =
            "¿Está seguro de eliminar el presupuesto?";

    public static final String TITULO_PASSWORD_INCORRECTA = "Contraseña incorrecta";
    public static final String BODY_PASSWORD_INCORRECTA = "La contraseña es incorrecta";
    public static final String TITULO_PASSWORD_NO_VALIDA = "Contraseña no valida";
    public static final String BODY_PASSWORD_NO_VALIDA = "La contraseña solo puede llevar números";
    public static final String TITULO_PASSWORD_NO_RELLENA = "Contraseña vacia";
    public static final String BODY_PASSWORD_NO_RELLENA = "Introduzca una contraseña";

    public static final String TITULO_CREDENCIALES_INCORRECTAS = "Credenciales incorrectas";
    public static final String BODY_CREDENCIALES_INCORRECTAS = "No existe un usuario con esas credenciales";
    public static final String TITULO_CREDENCIALES_NO_VALIDAS = "Credenciales no validas";
    public static final String BODY_CREDENCIALES_NO_VALIDAS = "El id y la contraseña solo pueden " +
            "llevar numeros";
    public static final String TITULO_CREDENCIALES_NO_RELLENAS = "Credenciales vacias";
    public static final String BODY_CREDENCIALES_NO_RELLENAS = "Introduzca las dos credenciales";

    public static final String TITULO_CUENTA_NO_SELECCIONADA = "Cuenta no seleccionada";
    public static final String BODY_CUENTA_NO_SELECCIONADA = "No hay ninguna cuenta seleccionada";

    public static final String TITULO_CUENTA_ELIMINADA = "Cuenta eliminada";
    public static final String BODY_CUENTA_ELIMINADA = "La cuenta se eliminó exitosamente";

    public static final String TITULO_CUENTA_ACTUALIZADA = "Cuenta actualizada";
    public static final String BODY_CUENTA_ACTUALIZADA = "La cuenta se actualizó exitosamente";

    public static final String TITULO_CUENTA_NO_ACTUALIZADA = "Cuenta no actualizada";
    public static final String BODY_CUENTA_NO_ACTUALIZADA_ID = "La cuenta no se actualizó exitosamente, " +
            "ya está en uso el id administrado";
    public static final String BODY_CUENTA_NO_ACTUALIZADA_NUM_CUENTA = "La cuenta no se actualizó exitosamente, " +
            "ya está en uso el número de cuenta administrado";

    public static final String TITULO_CUENTA_NO_AGREGADA = "Cuenta no agregada";
    public static final String BODY_CUENTA_NO_AGREGADA = "La cuenta no se pudo agregar, ya existe";

    public static final String TITULO_CUENTA_AGREGADA = "Cuenta agregada";
    public static final String BODY_CUENTA_AGREGADA = "La cuenta se agregó exitosamente";

    public static final String TITULO_BIENVENIDA = "Usuario logeado";
    public static final String BODY_BIENVENIDA = "Bienvenid@: ";

    public static final String TITULO_DEPOSITO_EXITOSO = "Deposito exitoso";
    public static final String BODY_DEPOSITO_EXITOSO = "Se han depositado exitosamente: ";
    public static final String TITULO_DEPOSITO_NO_EXITOSO = "Deposito no exitoso";
    public static final String BODY_DEPOSITO_NO_EXITOSO = "No se ha podido depositar";

    public static final String TITULO_RETIRO_EXITOSO = "Retiro exitoso";
    public static final String BODY_RETIRO_EXITOSO = "Se retiraron exitosamente: ";
    public static final String TITULO_RETIRO_NO_EXITOSO = "Retiro no exitoso";
    public static final String BODY_RETIRO_NO_EXITOSO = "Su cuenta no tiene el saldo suficiente a retirar";

    public static final String TITULO_TRANSFERENCIA_EXITOSA = "Transferencia exitosa";
    public static final String BODY_TRANSFERENCIA_EXITOSA = "Se transfirieron exitosamente: ";
    public static final String TITULO_TRANSFERENCIA_NO_EXITOSA = "Transferencia no exitosa";
    public static final String BODY_TRANSFERENCIA_NO_EXITOSA_DINERO = "Su cuenta no tiene el saldo suficiente a transferir";

    public static final String TITULO_CATEGORIA_NO_SELECCIONADA = "Categoria no seleccionada";
    public static final String BODY_CATEGORIA_NO_SELECCIONADA = "No hay ninguna Categoria seleccionada";

    public static final String TITULO_CATEGORIA_NO_AGREGADA = "Categoria no agregada";
    public static final String BODY_CATEGORIA_NO_AGREGADA = "La categoria no se pudo agregar, ya existe";

    public static final String TITULO_CATEGORIA_AGREGADA = "Categoria agregada";
    public static final String BODY_CATEGORIA_AGREGADA = "La categoria se agregó exitosamente";

    public static final String TITULO_CATEGORIA_ELIMINADA = "Categoria eliminada";
    public static final String BODY_CATEGORIA_ELIMINADA = "La categoria se eliminó exitosamente";

    public static final String TITULO_CATEGORIA_NO_ELIMINADA = "Categoria no eliminada";
    public static final String BODY_CATEGORIA_NO_ELIMINADA = "La categoria no se eliminó, debido a que tiene un presupuesto asociado";

    public static final String TITULO_CATEGORIA_ACTUALIZADA = "Categoria actualizada";
    public static final String BODY_CATEGORIA_ACTUALIZADA = "La categoria se actualizó exitosamente";

    public static final String TITULO_CATEGORIA_NO_ACTUALIZADA = "Categoria no actualizada";
    public static final String BODY_CATEGORIA_NO_ACTUALIZADA = "La categoria no se actualizó exitosamente," +
            " ya existe";

    public static final String TITULO_PRESUPUESTO_SUPERADO = "Presupuesto superado";
    public static final String BODY_PRESUPUESTO_SUPERADO = "No se puede realizar el movimiento ya que" +
            " superaría el presupuesto en su categoria";

    public static final String TITULO_PRESUPUESTO_NO_AGREGADO = "Presupuesto no agregado";
    public static final String BODY_PRESUPUESTO_NO_AGREGADO = "El presupuesto no se pudo agregar, ya existe";

    public static final String TITULO_PRESUPUESTO_AGREGADO = "Presupuesto agregado";
    public static final String BODY_PRESUPUESTO_AGREGADO = "El presupuesto se agregó exitosamente";

    public static final String TITULO_PRESUPUESTO_NO_SELECCIONADO = "Presupuesto no seleccionado";
    public static final String BODY_PRESUPUESTO_NO_SELECCIONADO = "No hay ningún presupuesto seleccionado";

    public static final String TITULO_PRESUPUESTO_ELIMINADO = "Presupuesto eliminado";
    public static final String BODY_PRESUPUESTO_ELIMINADO = "El presupuesto se eliminó exitosamente";

    public static final String TITULO_PRESUPUESTO_NO_ELIMINADO = "Presupuesto no eliminado";
    public static final String BODY_PRESUPUESTO_NO_ELIMINADO = "El presupuesto no se eliminó, debido a que tiene una cuenta asociada";

    public static final String TITULO_PRESUPUESTO_ACTUALIZADO = "Presupuesto actualizado";
    public static final String BODY_PRESUPUESTO_ACTUALIZADO = "El presupuesto se actualizó exitosamente";

    public static final String TITULO_PRESUPUESTO_NO_ACTUALIZADO = "Presupuesto no actualizado";
    public static final String BODY_PRESUPUESTO_NO_ACTUALIZADO = "El presupuesto no se actualizó exitosamente," +
            " ya existe";

    public static final String TITULO_PRESUPUESTO_TOPE_INVALIDO = "Tope invalido";
    public static final String BODY_PRESUPUESTO_TOPE_INVALIDO = "El tope del presupuesto es mayor al monto ya gastado";
}