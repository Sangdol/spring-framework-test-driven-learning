package com.sangdol.springSimple;

import com.google.common.collect.ImmutableMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author hugh
 */
@RestController
@RequestMapping("home")
public class HomeController {

    @RequestMapping
    public String helloWorld(String name) {
        return "Hello " + name + "!";
    }

    @RequestMapping("map")
    public Map<String, String> helloMap(String name) {
        return ImmutableMap.of("Hello", name);
    }
}
