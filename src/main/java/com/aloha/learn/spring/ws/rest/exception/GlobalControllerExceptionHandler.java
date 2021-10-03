package com.aloha.learn.spring.ws.rest.exception;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.aloha.learn.spring.ws.rest.user.UserNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    ExceptionResponse handleUserNotFoundException(HttpServletRequest request, Exception exception) {
        return new ExceptionResponse(new Date(), exception.getMessage(), request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    ExceptionResponse handleInternalException(HttpServletRequest request, Exception exception) {
        return new ExceptionResponse(new Date(), exception.getMessage(), request.getRequestURI());
    }

}
