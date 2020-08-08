package com.henry.javaee.control;

import java.util.logging.Level;
import java.util.logging.Logger;

public class FatalLogger {

    private static final Logger logger = Logger.getLogger(FatalLogger.class.getName());
    public void fatal(Throwable throwable) {
        logger.log(Level.SEVERE, throwable.getMessage(), throwable);
    }
}
