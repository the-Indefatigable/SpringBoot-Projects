package com.alam.dependencyinjection;

import com.alam.dependencyinjection.cars.Audi;
import com.alam.dependencyinjection.cars.Lambo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DependencyInjectionApplication {

    public static void main(String[] args) {
//        SpringApplication.run(DependencyInjectionApplication.class, args);
        ApplicationContext context = SpringApplication.run(DependencyInjectionApplication.class, args);
        Audi audi = context.getBean(Audi.class);
        audi.getSpec();
        Lambo lambo = context.getBean(Lambo.class);
        lambo.getSpec();
    }

}
