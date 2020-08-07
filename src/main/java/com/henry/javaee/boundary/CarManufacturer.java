package com.henry.javaee.boundary;

import com.henry.javaee.entity.Car;
import com.henry.javaee.entity.CarCreated;
import com.henry.javaee.entity.Specification;
import com.henry.javaee.control.CarFactory;
import com.henry.javaee.control.CarRepository;


import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;


@Stateless
public class CarManufacturer {

    @Inject
    CarFactory carFactory;

    @Inject
    CarRepository carRepository;

    @Inject
    Event<CarCreated> carCreated;

    public Car manufactureCar(Specification specification){
        Car car = carFactory.createCar(specification);
        carRepository.store(car);
        carCreated.fire(new CarCreated(car.getIdentifier()));
        return car;
    }

}
