package com.henry.javaee.entity;

import com.henry.javaee.control.CarCreationException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CarCreationExceptionMapper  implements ExceptionMapper<CarCreationException> {
    @Override
    public Response toResponse(CarCreationException e) {
        return Response.serverError()
                .header("X-Car-Error", e.getMessage())
                .entity(e.getMessage())
                .build();
    }
}
