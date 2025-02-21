package com.alam.dependencyinjection.engines;

import com.alam.dependencyinjection.interfaces.Engine;
import org.springframework.stereotype.Component;

@Component
public class General implements Engine {
    String type = "General Gas ";

    public String getType() {
        return type;
    }
}
