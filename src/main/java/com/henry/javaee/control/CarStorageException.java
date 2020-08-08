package com.henry.javaee.control;

import javax.ejb.ApplicationException;
import javax.ws.rs.ApplicationPath;

@ApplicationException(rollback = true)
public class CarStorageException extends Exception {
    public CarStorageException(String message) {
        super(message);
    }
}
