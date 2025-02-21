package com.alam.dependencyinjection.cars;

import com.alam.dependencyinjection.engines.General;
import com.alam.dependencyinjection.interfaces.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Audi implements Car {

    //we have not instantiated it
    // we do not wanna create it manually
    //we gonna use autoWire annotation

    @Autowired
    General engine;

    @Override
    public void getSpec() {
       System.out.println("This is Audi with an" + engine.getType() + " engine");
    }
}
