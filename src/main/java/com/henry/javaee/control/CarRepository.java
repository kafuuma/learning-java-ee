package com.henry.javaee.control;

import com.henry.javaee.entity.Car;
import com.henry.javaee.entity.Color;
import com.henry.javaee.entity.EngineType;

import java.util.Arrays;
import java.util.List;


public class CarRepository {
    public void store(Car car) {
        // dummy error on creation
        System.out.println("persisted car = " + car);
    }

    public List<Car> loadCars() {
        // dummy creation
        return Arrays.asList(
                createCar("X123A234", Color.RED, EngineType.DIESEL),
                createCar("X234B345", Color.BLACK, EngineType.ELECTRIC),
                createCar("X345C456", Color.GREY, EngineType.PETROL)
        );
    }

    private static Car createCar(String identifier, Color color, EngineType engineType) {
        Car car = new Car();
        car.setIdentifier(identifier);
        car.setColor(color);
        car.setEngineType(engineType);
        return car;
    }
}
