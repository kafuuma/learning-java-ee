package com.henry.javaee.boundary;

import com.henry.javaee.control.CarStorageException;
import com.henry.javaee.entity.Car;
import com.henry.javaee.entity.EngineType;
import com.henry.javaee.entity.Specification;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.stream.JsonCollectors;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

@Path("cars")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CarResource {
    @Inject
    CarManufacturer carManufacturer;

    @Resource
    ManagedExecutorService mes;

    @GET
    public List<Car> getCars() {
        return carManufacturer.retrieveCars();
    }

    @POST
    public CompletionStage<Response> createCarAsync(Specification specification) {
        return CompletableFuture.supplyAsync(() -> createCar(specification), mes);
    }

    // just as an example
    @POST
    @Path("timeout")
    public void createCarAsyncTimeout(Specification specification,
                                      @Suspended AsyncResponse asyncResponse) {
        asyncResponse.setTimeout(10, TimeUnit.SECONDS);
        asyncResponse.setTimeoutHandler(response ->
                response.resume(Response.status(Response.Status.SERVICE_UNAVAILABLE).build()));

        mes.execute(() -> asyncResponse.resume(createCar(specification)));
    }


    private Response createCar(Specification specification) throws CarStorageException {
        carManufacturer.manufactureCar(specification);
        return Response.status(Response.Status.NO_CONTENT).build();
    }



}
