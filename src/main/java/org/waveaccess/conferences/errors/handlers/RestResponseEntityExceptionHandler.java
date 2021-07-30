package org.waveaccess.conferences.errors.handlers;

import com.auth0.jwt.exceptions.JWTDecodeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.waveaccess.conferences.dto.ExceptionDto;
import org.waveaccess.conferences.errors.exceptions.BadRequestException;
import org.waveaccess.conferences.errors.exceptions.NoAuthoritiesException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionDto> handleBadRequest(BadRequestException exception) {
        return ResponseEntity.badRequest()
                .body(ExceptionDto.builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .message(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(NoAuthoritiesException.class)
    public ResponseEntity<ExceptionDto> handleNoPermission(NoAuthoritiesException exception) {
        return ResponseEntity.badRequest()
                .body(ExceptionDto.builder()
                        .code(HttpStatus.FORBIDDEN.value())
                        .message(exception.getMessage())
                        .build());
    }
}