package com.prvendasstore.vendas.api.controller;


import com.prvendasstore.vendas.api.ApiErrors;
import com.prvendasstore.vendas.excpection.RegraNegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {


    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleValidationException(RegraNegocioException ex) {
        String msgError = ex.getMessage();
        return new ApiErrors(msgError);
    }
}
