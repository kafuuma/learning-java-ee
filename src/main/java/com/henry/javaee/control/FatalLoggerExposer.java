package com.henry.javaee.control;


import javax.enterprise.inject.Produces;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FatalLoggerExposer {


    @Produces
    public Consumer<Throwable> exposeFatalLogger() {
//        return e -> e.printStackTrace();
        return Throwable::printStackTrace;
    }


}
