package com.henry.javaee.cars.control;

import com.henry.javaee.cars.entity.Color;

import javax.enterprise.inject.Produces;

public class DefaultColorExposer {

    @Produces
    @Diesel
    public Color exposeDefalutColor(){
        // ..
        return Color.RED;
    }
}
