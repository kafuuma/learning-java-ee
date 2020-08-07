package com.henry.javaee.control;

import com.henry.javaee.entity.Color;

import javax.enterprise.inject.Produces;

public class DefaultColorExposer {

    @Produces
    @Diesel
    public Color exposeDefalutColor(){
        // ..
        return Color.RED;
    }
}
