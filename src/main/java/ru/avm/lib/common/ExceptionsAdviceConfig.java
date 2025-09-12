package ru.avm.lib.common;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.avm.lib.common.dto.ExceptionDto;
import ru.avm.lib.common.exception.ApiException;
import ru.avm.lib.common.exception.NotAuthorizedException;
import ru.avm.lib.common.exception.NotFoundException;
import ru.avm.lib.common.exception.NotImplementedException;
import ru.avm.lib.common.mapper.ExceptionMapper;

import java.lang.reflect.UndeclaredThrowableException;

@RequiredArgsConstructor

@Slf4j
@RestControllerAdvice
public class ExceptionsAdviceConfig {

    private final ExceptionMapper exceptionMapper;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handleException(Exception exception) {
        log.error("unexpected exception", exception);
        return exceptionMapper.toDto(exception);
    }

    @ExceptionHandler(UndeclaredThrowableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionDto sneakyException(UndeclaredThrowableException ex) {
        log.error("sneaky exception", ex);
        return exceptionMapper.toDto(ex.getCause());
    }

    @ExceptionHandler(FeignException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<String> feignException(FeignException ex) {
        log.error("proxy exception", ex);
        return new ResponseEntity<>(ex.contentUTF8(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotImplementedException.class)
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public ExceptionDto handleNotImplemented(NotImplementedException exception) {
        return exceptionMapper.toDto(exception);
    }

    @ExceptionHandler(NotAuthorizedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ExceptionDto handleNotAuthorizedException(NotAuthorizedException exception) {
        return exceptionMapper.toDto(exception);
    }

    @ExceptionHandler(ApiException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handleApiException(ApiException exception) {
        return exceptionMapper.toDto(exception);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDto handleNotFoundException(NotFoundException exception) {
        return exceptionMapper.toDto(exception);
    }

}
