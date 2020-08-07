package com.henry.javaee.cars.boundary;

import com.henry.javaee.cars.entity.Car;
import com.henry.javaee.cars.entity.CarCreated;
import com.henry.javaee.cars.entity.Specification;
import com.henry.javaee.cars.control.CarFactory;
import com.henry.javaee.cars.control.CarRepository;


import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.util.List;


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

    public List<Car> retrieveCars(){
        return carRepository.loadCars();
    }

}
