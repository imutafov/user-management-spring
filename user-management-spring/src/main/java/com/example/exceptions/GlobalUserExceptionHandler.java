/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author msol-pc
 */
@ControllerAdvice
public class GlobalUserExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalUserExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception ex) {

        ExceptionResponse response = new ExceptionResponse();
        response.setCode(HttpStatus.NOT_FOUND.value());
        response.setDescription(ex.getMessage());
        LOGGER.error("User not found", ex);
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }
}
