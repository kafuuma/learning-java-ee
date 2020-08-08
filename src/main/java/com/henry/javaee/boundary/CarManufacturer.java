package com.henry.javaee.boundary;

import com.henry.javaee.control.*;
import com.henry.javaee.entity.Car;
import com.henry.javaee.entity.CarCreated;
import com.henry.javaee.entity.Specification;


import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.PersistenceContext;

import java.awt.*;
import java.util.List;
import java.util.concurrent.Future;


@Stateless
public class CarManufacturer {

    @Inject
    CarFactory carFactory;

    @PersistenceContext
    CarRepository carRepository;

    @Inject
    CarProcessor carProcessor;

    @Inject
    Event<CarCreated> carCreated;

    @Resource
    ManagedExecutorService ms;



    public Car manufactureCar(Specification specification) throws CarStorageException {
        Car car = carFactory.createCar(specification);
        carRepository.store(car);
        ms.execute(()-> carProcessor.processNewCar(car));
        carProcessor.processNewCarAsync(car);
        carCreated.fireAsync( new  CarCreated(car.getIdentifier()));
//        Future<String> resultFeature =  carProcessor.processNewCar(car);
        return car;
    }

    public List<Car> retrieveCars() {

//        return entityManager.createNamedQuery(Car.FIND_ALL, Car.class).getResultList();
        return carRepository.loadCars();
    }


}


