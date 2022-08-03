package com.psl.alp.LicenseCatalogue.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoLicensesExistException extends RuntimeException {

    private String message;

    public NoLicensesExistException() {}

    public NoLicensesExistException(String message) {
        super(message);
    }


}   