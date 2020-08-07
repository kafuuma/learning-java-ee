package com.henry.javaee.control;

import com.henry.javaee.entity.CarCreated;

import javax.enterprise.event.Observes;

public class CarCreationListener {
    public void onCarCreation(@Observes CarCreated carCreated){
        //...
        System.out.println("new car created id "+ carCreated.getIdentifier());
    }
}
