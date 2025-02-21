package com.alam.dependencyinjection.cars;

import com.alam.dependencyinjection.interfaces.Car;
import com.alam.dependencyinjection.interfaces.Engine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Lambo implements Car {

    //field Injection
//    @Qualifier("v8")
//    @Autowired
    Engine engine;

    // Constructor Injection
//     public Lambo(@Qualifier("v8") Engine engine) {
//        this.engine = engine;
//    }

    //setter injection

    @Autowired
    public void setEngine(@Qualifier("w12") Engine engine) {
        this.engine = engine;
    }

    @Override
    public void getSpec() {
        System.out.println("This is Audi with an" + engine.getType() + " engine");
    }
}
