package com.springboot.hello.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/get-api")
public class HelloController {

    @RequestMapping(name = "/hello", method = RequestMethod.GET)
    public String hello(){
        return "HelloWorld";
    }

    @GetMapping("/name")
    public String getName(){
        return "Junha";
    }

    @GetMapping("/variable1/{variable}")
    public String getVariable1(@PathVariable String variable){
        return variable;
    }

    @GetMapping("/variable2/{variable}")
    public String getVariable2(@PathVariable String variable){
        return variable;
    }

    @GetMapping("/request1")
    public String getVariable3(@RequestParam String name,
                               @RequestParam String email,
                               @RequestParam String organization){
        return String.format("%s %s %s", name, email, organization);
    }

}
