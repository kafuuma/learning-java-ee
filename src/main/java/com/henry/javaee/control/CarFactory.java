package com.henry.javaee.control;

import com.henry.javaee.entity.Car;

import com.henry.javaee.entity.Specification;

import javax.inject.Inject;



public class CarFactory {

    @Inject
    IdentifierAccessor identifierAccessor;

    public Car createCar(Specification specification) {
        Car car = new Car();
        car.setIdentifier(identifierAccessor.retrieveCarIdentification(specification));
        car.setColor(specification.getColor());
        car.setEngineType(specification.getEngineType());
        return car;
    }

}