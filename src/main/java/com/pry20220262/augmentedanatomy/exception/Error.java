package com.pry20220262.augmentedanatomy.exception;

public enum Error {

    INVALID_DATA(1001, "Invalid Data"),
    USER_NOT_FOUND(1002, "User not found :("),
    DATA_NOT_FOUND(900, "Data not found it, try later"),
    USER_EXISTS(1003, "El usuario ingresado ya existe. Intente nuevamente"),
    USER_PIN_NOT_MATCH(1003, "El pin ingresado no pertenece al usuario"),
    INVALID_OLD_PASSWORD(1003, "El password anterior, no pertenece al usuario"),
    PHOTO_NOT_UPLOADED(1003, "La foto de perfil no se pudo cargar"),
    LIST_IS_EMPTY(1003, "No se han encontrado datos :("),
    ;

    private final int codError;

    private final String message;

    Error(int codError, String message) {
        this.codError = codError;
        this.message = message;
    }

    public int getCodError() {
        return this.codError;
    }

    public String getMessage() {
        return this.message;
    }


}
