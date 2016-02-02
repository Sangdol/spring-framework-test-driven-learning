package com.sangdol.springSimple;

import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author hugh
 */
@RestController
@RequestMapping("validation")
public class ValidationController {

    @Setter // needed for setting posted properties
    static class Person {
        @NotNull
        String name;

        @Range(max = 150)
        int age;

        @Email
        String email;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String home(@ModelAttribute Person person) {
        return person.name + " " + person.age;
    }

    @RequestMapping(value = "valid", method = RequestMethod.POST)
    public String valid(@Valid @ModelAttribute Person person) {
        return person.name + " " + person.age;
    }

    @RequestMapping(value = "binding-result", method = RequestMethod.POST)
    public String bindingResult(@Valid @ModelAttribute Person person, BindingResult result) {
        return result.hasErrors() + " " + result.getErrorCount();
    }
}
