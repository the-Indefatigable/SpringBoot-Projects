package com.alam.dependencyinjection.engines;

import com.alam.dependencyinjection.interfaces.Engine;
import org.springframework.stereotype.Component;

@Component
public class W12 implements Engine {
    @Override
    public String getType() {
        return "W12";
    }
}
