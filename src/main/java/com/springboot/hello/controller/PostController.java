package com.springboot.hello.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/post-api")
public class PostController {

    @RequestMapping(value="/domain", method= RequestMethod.POST)
    public String postExample() {
        return "Hello Post API";
    }
}
