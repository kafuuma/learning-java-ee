package com.henry.javaee.boundary;

import com.henry.javaee.entity.Car;
import com.henry.javaee.entity.CarCreated;
import com.henry.javaee.entity.EngineType;
import com.henry.javaee.entity.Specification;
import com.henry.javaee.control.CarFactory;
import com.henry.javaee.control.CarRepository;


import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;


@Stateless
public class CarManufacturer {
    @Inject
    CarFactory carFactory;

    @Inject
    CarRepository carRepository;

    public Car manufactureCar(Specification specification) {
        Car car = carFactory.createCar(specification);
        carRepository.store(car);
        return car;
    }

    public List<Car> retrieveCars() {
        return carRepository.loadCars();
    }

    public List<Car> retrieveCars(EngineType filter) {
        return carRepository.loadCars().stream()
                .filter(c -> c.getEngineType() == filter)
                .collect(Collectors.toList());
    }

    public Car retrieveCar(String identifier) {
        Car car = new Car();
        car.setIdentifier(identifier);
        return car;
    }
}


