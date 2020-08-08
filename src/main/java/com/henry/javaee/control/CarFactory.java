package com.henry.javaee.control;

import com.henry.javaee.entity.Car;
import com.henry.javaee.entity.Color;
import com.henry.javaee.entity.Specification;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


public class CarFactory {

    @Inject
    @Diesel
    Color defaultCarColor;

    @Inject
    @Config("identifier.prefix")
    String identifierPrefix;

    @Resource
    ManagedScheduledExecutorService ms;

    @Transactional(rollbackOn = CarStorageException.class)
    public Car createCar(Specification specification) {
        Car car = new Car();
        car.setIdentifier(identifierPrefix +"-"+ UUID.randomUUID().toString());
        car.setColor(specification.getColor() ==null ? defaultCarColor : specification.getColor());
        car.setEngineType(specification.getEngineType());
        return car;
    }

    @PostConstruct
    public void activateTimer(){
        ms.scheduleAtFixedRate(this::doSomething, 60,10, TimeUnit.SECONDS);
    }

    public void doSomething(){
        System.out.println("print something");
    }
}