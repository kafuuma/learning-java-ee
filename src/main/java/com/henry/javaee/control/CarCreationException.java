package com.henry.javaee.control;


import javax.ejb.ApplicationException;

@ApplicationException
public class CarCreationException extends RuntimeException {

    public CarCreationException(String message) {
        super(message);
    }

}
