package com.airteldemo.demo.Exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;


import javax.servlet.http.HttpServletRequest;



@ControllerAdvice
public class ErrorHttpMessageNotReadableException {
    private static final SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleException(HttpMessageNotReadableException exception, HttpServletRequest request) {
        String message="INVALID JSON";

        String path=request.getRequestURI();

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String responeTimeStamp=timeStampFormat.format(timestamp);

        BadRequestErrorTemplate template=new BadRequestErrorTemplate();
        template.setError("Bad Request");
        template.setMessage(message);
        template.setPath(path);
        template.setTimestamp(responeTimeStamp);
        template.setStatus("400");

        return new ResponseEntity(template, HttpStatus.BAD_REQUEST);
    }


    @Data
    public class BadRequestErrorTemplate {

        private String timestamp;
        private String status;
        private String error;
        private String message;
        private String path;
    }

}
