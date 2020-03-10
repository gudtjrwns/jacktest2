package com.example.jacktest2.exception;

public class NoticeNotFoundException extends EntityNotFoundException {

    public NoticeNotFoundException() {
        super();
    }

    public NoticeNotFoundException(String message) {
        super(message);
    }

    public NoticeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoticeNotFoundException(Throwable cause) {
        super(cause);
    }

}
