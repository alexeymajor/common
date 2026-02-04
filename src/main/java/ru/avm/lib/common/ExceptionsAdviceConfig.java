package ru.avm.lib.common;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
        val ex = exceptionMapper.toDto(exception);
        log.error(ex.toString(), exception);
        return ex;
    }

    @ExceptionHandler(UndeclaredThrowableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionDto sneakyException(UndeclaredThrowableException exception) {
        val ex = exceptionMapper.toDto(exception.getCause());
        log.error(ex.toString(), exception);
        return ex;
    }

    @ExceptionHandler(FeignException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<String> feignException(FeignException exception) {
        val ex = exceptionMapper.toDto(exception);
        log.error(ex.toString(), exception);
        val statusCode = HttpStatusCode.valueOf(exception.status());
        return new ResponseEntity<>(exception.contentUTF8(), statusCode);
    }

    @ExceptionHandler(NotImplementedException.class)
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public ExceptionDto handleNotImplemented(NotImplementedException exception) {
        val ex = exceptionMapper.toDto(exception);
        log.error(ex.toString(), exception);
        return ex;
    }

    @ExceptionHandler(NotAuthorizedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ExceptionDto handleNotAuthorizedException(NotAuthorizedException exception) {
        val ex = exceptionMapper.toDto(exception);
        log.error(ex.toString(), exception);
        return ex;
    }

    @ExceptionHandler(ApiException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handleApiException(ApiException exception) {
        val ex = exceptionMapper.toDto(exception);
        log.error(ex.toString(), exception);
        return ex;
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDto handleNotFoundException(NotFoundException exception) {
        val ex = exceptionMapper.toDto(exception);
        log.error(ex.toString(), exception);
        return ex;
    }

}
