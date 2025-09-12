package ru.avm.lib.common.exception;


public class NotImplementedException extends ApiException {
    public NotImplementedException(String message) {
        super(message);
    }
    public NotImplementedException() {
        super("Метод не готов к использованию");
    }
}
