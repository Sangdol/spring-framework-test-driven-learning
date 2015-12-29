package com.sangdol.springSimple;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author hugh
 */
@Controller
@RequestMapping("home")
public class HomeController {

    @RequestMapping
    @ResponseBody
    public String helloWorld(String name) {
        return "Hello " + name + "!";
    }

    @RequestMapping("map")
    @ResponseBody
    public Map<String, String> helloMap(String name) {
        return ImmutableMap.of("Hello", name);
    }
}
