package com.henry.javaee.boundary;

import com.henry.javaee.control.CarStorageException;
import com.henry.javaee.entity.Car;
import com.henry.javaee.entity.EngineType;
import com.henry.javaee.entity.Specification;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.stream.JsonCollectors;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("cars")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CarResource {

    @Inject
    CarManufacturer carManufacturer;

    @Context
    UriInfo uriInfo;

    @GET
    public JsonArray retrieveCars(@NotNull @QueryParam("filter") EngineType engineType) {
        return carManufacturer.retrieveCars()
                .stream()
                .map(c -> Json.createObjectBuilder()
                        .add("identifier", c.getIdentifier())
                        .add("color", c.getColor().name())
                        .add("engine", c.getEngineType().name())
                        .build())
                .collect(JsonCollectors.toJsonArray());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCar(@Valid @NotNull Specification specification) throws CarStorageException {
        Car car = carManufacturer.manufactureCar(specification);

        URI uri = uriInfo.getBaseUriBuilder()
                .path(CarResource.class)
                .path(CarResource.class, "retrieveCar")
                .build(car.getIdentifier());

        return Response.created(uri).build();
    }

    @GET
    @Path("{id}")
    public Car retrieveCar(@PathParam("id") String identifier) {
        return carManufacturer.retrieveCar(identifier);
    }

}
