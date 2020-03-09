package com.example.jacktest2.config;

import com.example.jacktest2.dao.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;


@RestControllerAdvice
public class RestControllerExceptionAdvice {

    private static final Logger log = LoggerFactory.getLogger(RestControllerExceptionAdvice.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public ErrorResponse handlerRuntimeException(RuntimeException e){
        log.error("================= Handler RuntimeException =================");
        log.error(e.getMessage());
        return new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "RuntimeException : "+e.getMessage()
        );
    }
}
