package com.henry.javaee.control;

import com.henry.javaee.entity.Car;

import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Schedule;
import javax.ejb.Startup;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class CarCache {

    @PersistenceContext
    EntityManager entityManager;
    /*
    * private final Map<String, Car> cache = new HashMap<>();
    * we use a  concurrent hashMap
    */
    private final Map<String, Car> cache = new ConcurrentHashMap<>();

    @PostConstruct
    private void initCache(){
        loadCars();
    }

    @Schedule(hour = "*")
    public void loadCars() {
        List<Car> cars = entityManager.createNamedQuery(Car.FIND_ALL, Car.class).getResultList();
//        cars.forEach(car -> cache.put(car.getIdentifier(), car));
        cars.forEach(this::cacheCar);
    }

    public List<Car> retrieveCars() {
        return new ArrayList<>(cache.values());
    }

    public void cacheCar(Car car) {
        cache.put(car.getIdentifier(), car);
    }
}
