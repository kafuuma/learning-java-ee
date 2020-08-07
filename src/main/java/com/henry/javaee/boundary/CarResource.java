package com.henry.javaee.boundary;

import com.henry.javaee.entity.Car;
import com.henry.javaee.entity.Specification;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("cars")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CarResource {

    @Inject
    CarManufacturer carManufacturer;

    @GET
    public List<Car>  retrieveCars(){

        return carManufacturer.retrieveCars();

    }


    @POST
    public void createCar(Specification specification){
        carManufacturer.manufactureCar(specification);
    }

}
