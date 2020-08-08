package com.henry.javaee.boundary;

import com.henry.javaee.control.*;
import com.henry.javaee.entity.Car;
import com.henry.javaee.entity.CarCreated;
import com.henry.javaee.entity.EngineType;
import com.henry.javaee.entity.Specification;


import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;


@Stateless
public class CarManufacturer {

    @Inject
    CarFactory carFactory;

    @PersistenceContext
    EntityManager entityManager;

    @Inject
    CarCache carCache;

    @Inject
    FatalLogger fatalLogger;

    //we can also logg
//    @Inject
//    Consumer<FatalLoggerExposer> fatalLogger;

    @Interceptors(ProcessTrackingInterceptor.class)
    public Car manufactureCar(Specification specification) throws CarStorageException {
        Car car = carFactory.createCar(specification);
//        entityManager.persist(car);
//        throw  new CarStorageException("!");
        carCache.cacheCar(car); //we can also implement an interceptor to cache the car

        try {

        }catch (Exception e){
            fatalLogger.fatal(e);
//            fatalLogger.accept(e);
        }
        return car;
    }

    public List<Car> retrieveCars() {

//        return entityManager.createNamedQuery(Car.FIND_ALL, Car.class).getResultList();
        return carCache.retrieveCars();
    }

//    public List<Car> retrieveCars(EngineType filter) {
//        return carRepository.loadCars().stream()
//                .filter(c -> c.getEngineType() == filter)
//                .collect(Collectors.toList());
//    }

    public Car retrieveCar(String identifier) {
        Car car = new Car();
        car.setIdentifier(identifier);
        return car;
    }
}


