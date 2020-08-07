package com.henry.javaee.cars.control;

import com.henry.javaee.cars.entity.CarCreated;

import javax.enterprise.event.Observes;

public class CarCreationListener {
    public void onCarCreation(@Observes CarCreated carCreated){
        //...
        System.out.println("new car created id "+ carCreated.getIdentifier());
    }
}
