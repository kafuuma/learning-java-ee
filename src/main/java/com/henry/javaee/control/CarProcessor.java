package com.henry.javaee.control;

import com.henry.javaee.entity.Car;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import java.util.concurrent.Future;
import java.util.concurrent.locks.LockSupport;

@Stateless
public class CarProcessor {


//    @Asynchronous
//    public Future<String> processNewCar(Car car) {
//        LockSupport.parkNanos(2_000_000_000L);
//        String result ="processed:  "+car;
//        System.out.println(result);
//        return  new AsyncResult<>(result);
//    }

    @Asynchronous
    public void processNewCarAsync(Car car) {
        LockSupport.parkNanos(2_000_000_000L);
        String result ="processed:  "+car;
        System.out.println(result);
    }

    @Asynchronous
    public void processNewCar(Car car) {
        LockSupport.parkNanos(2_000_000_000L);
        String result ="processed:  "+car;
        System.out.println(result);
    }
}
