package ru.avm.lib.common.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ApiException extends Exception {
    public ApiException(String message) {
        super(message);
    }
}
