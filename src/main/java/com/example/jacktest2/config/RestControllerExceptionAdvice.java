package com.example.jacktest2.config;

import com.example.jacktest2.dao.ErrorResponse;
import com.example.jacktest2.exception.NoticeTitleAlreadyExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;


@RestControllerAdvice
public class RestControllerExceptionAdvice {

    private static final Logger log = LoggerFactory.getLogger(RestControllerExceptionAdvice.class);



    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorResponse> handlerIOException(IOException e) {

        log.error("================= Handler IOException =================");
        log.error(e.getMessage());

        ErrorResponse message = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "파일을 찾을 수 없습니다.");

        return new ResponseEntity(message, HttpStatus.BAD_REQUEST);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> handlerBindException(BindException e) {

        log.error("================= Handler BindException =================");
        log.error(e.getMessage());

        ErrorResponse message = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "저장 실패하였습니다.");

        return new ResponseEntity(message, HttpStatus.NOT_FOUND);
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(javax.persistence.EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerEntityNotFoundException(EntityNotFoundException e) {

        log.error("================= Handler EntityNotFoundException =================");
        log.error(e.getMessage());

        ErrorResponse message = new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());

        return new ResponseEntity(message, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(NoticeTitleAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handlerNoticeTitleAlreadyExistException(NoticeTitleAlreadyExistException e) {

        log.error("================= Handler NoticeTitleAlreadyExistException =================");
        log.error("Notice title already exist");

        ErrorResponse message = new ErrorResponse(HttpStatus.OK.value(), "동일한 제목의 게시글이 존재합니다. ");

        return new ResponseEntity(message, HttpStatus.OK);
    }

}
