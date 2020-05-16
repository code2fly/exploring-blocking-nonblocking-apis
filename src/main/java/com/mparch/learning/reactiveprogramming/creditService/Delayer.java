package com.mparch.learning.reactiveprogramming.creditService;

import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.jmx.export.annotation.ManagedOperationParameters;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@ManagedResource
@Component("creditDelayer")
public class Delayer {

    private int delay;


    public Delayer() {
        System.out.println("******* test mbean for account service delayer being created");
        delay = 0;
    }

    @ManagedOperation
    @ManagedOperationParameters({
            @ManagedOperationParameter(name = "delayInMillis" ,description = "the amount of delay to be introduced in credit check service")
    })
    public void setDelayInMillis( int delayInMillis) {
        delay = delayInMillis;
    }

    public int getDelay() {
        return delay;
    }
}
