package com.henry.javaee.control;

import com.henry.javaee.entity.CarCreated;

import javax.enterprise.event.Observes;
import javax.enterprise.event.ObservesAsync;
import java.util.concurrent.locks.LockSupport;

public class CarCreationListener {
    public void onCarCreation(@ObservesAsync CarCreated carCreated){

        LockSupport.parkNanos(2_000_000_000L);
        System.out.println("new car created id "+ carCreated.getIdentifier());
    }
}
