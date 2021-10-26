package com.homework.simpleidus.api.handler;

import com.homework.simpleidus.api.exception.ExistException;
import com.homework.simpleidus.api.exception.NotFoundException;
import com.homework.simpleidus.api.exception.api.BadRequestException;
import com.homework.simpleidus.api.exception.api.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConversionFailedException.class)
    public ResponseEntity<Object> handleConversionFailed(RuntimeException exception, WebRequest request) {
        log.error(exception.getMessage(), exception);
        return handleException(exception, request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
    public ResponseEntity<Object> handleNotValid(RuntimeException exception, WebRequest request) {
        log.error(exception.getMessage(), exception);
        return handleException(exception, request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFound(RuntimeException exception, WebRequest request) {
        log.error(exception.getMessage(), exception);
        return handleException(exception, request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Object> handleUnauthorized(RuntimeException exception, WebRequest request) {
        log.error(exception.getMessage(), exception);
        return handleException(exception, request, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequest(RuntimeException exception, WebRequest request) {
        log.error(exception.getMessage(), exception);
        return handleException(exception, request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExistException.class)
    public ResponseEntity<Object> handleAlreadyExist(RuntimeException exception, WebRequest request) {
        log.error(exception.getMessage(), exception);
        return handleException(exception, request, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Object> handleException(Exception ex, WebRequest request, HttpStatus httpStatus) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), httpStatus, request);
    }
}
