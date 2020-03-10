package com.example.jacktest2.config;

import com.example.jacktest2.dao.ErrorResponse;
import com.example.jacktest2.exception.EntityNotFoundException;
import com.example.jacktest2.exception.NoticeTitleAlreadyExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;


@RestControllerAdvice
public class RestControllerExceptionAdvice {

    private static final Logger log = LoggerFactory.getLogger(RestControllerExceptionAdvice.class);


//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MissingServletRequestPartException.class)
//    public ErrorResponse handlerMissingServletRequestPartException(MissingServletRequestPartException e) {
//        log.error("================= Handler MissingServletRequestPartException =================");
//        log.error(e.getMessage());
//        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "파일을 찾을 수 없습니다.");
//    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResponse> handlerIllegalStateException(IllegalStateException e) {

        log.error("================= Handler IllegalStateException =================");
        log.error(e.getMessage());

        ErrorResponse message = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "파일을 찾을 수 없습니다.");

        return new ResponseEntity(message, HttpStatus.INTERNAL_SERVER_ERROR);
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
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerEntityNotFoundException(EntityNotFoundException e) {

        log.error("================= Handler EntityNotFoundException =================");
        log.error(e.getMessage());

        ErrorResponse message = new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage() + " 정보를 찾을 수 없습니다.");

        return new ResponseEntity(message, HttpStatus.NOT_FOUND);
    }


//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(ReplyNotFoundException.class)
//    public ResponseEntity<ErrorResponse> handlerReplyNotFoundException(ReplyNotFoundException e) {
//        log.error("================= Handler ReplyNotFoundException =================");
//        log.error(e.getMessage());
//        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), "댓글 정보를 확인할 수 없습니다.");
//    }
//
//
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(NoticeNotFoundException.class)
//    public ResponseEntity<ErrorResponse> handlerNoticeNotFoundException(NoticeNotFoundException e) {
//        log.error("================= Handler NoticeNotFoundException =================");
//        log.error(e.getMessage());
//        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), "게시글를 정보를 확인할 수 없습니다.");
//    }


    @ExceptionHandler(NoticeTitleAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handlerNoticeTitleAlreadyExistException(NoticeTitleAlreadyExistException e) {

        log.error("================= Handler NoticeTitleAlreadyExistException =================");
        log.error(e.getMessage());

        ErrorResponse message = new ErrorResponse(HttpStatus.OK.value(), "동일한 제목의 게시글이 존재합니다. ");

        return new ResponseEntity(message, HttpStatus.OK);
    }

}
