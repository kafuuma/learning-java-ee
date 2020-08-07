package com.henry.javaee.control;

import javax.annotation.PostConstruct;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.inject.Inject;
import javax.ws.rs.client.WebTarget;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

public class WeatherStation {

    private Set<WebTarget> targets;

    @Inject
    ManagedExecutorService ms;

    @PostConstruct
    private void init(){
        //...
    }

    public double getTemperatureForeCast(){

    List<CompletionStage<Double>> completionStages = invokeTemperatureTargets();

    return completionStages.stream()
            .reduce((l, r) -> l.thenCombine(r, Math::max))
            .map(c -> c.toCompletableFuture().join())
            .orElseThrow(() -> new IllegalStateException("No weather result available"));
    }

    private List<CompletionStage<Double>> invokeTemperatureTargets(){
        return targets.stream()
                .map(target -> target.request().rx().get(Double.class))
                .collect(Collectors.toList());
    }

}
