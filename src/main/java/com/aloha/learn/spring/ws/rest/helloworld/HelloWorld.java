package com.aloha.learn.spring.ws.rest.helloworld;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello-world")
public class HelloWorld {

    @Value("Hello World")
    private String name;
    
    @RequestMapping(method = RequestMethod.GET, path = "/static")
    public String helloWorld() {
        return "Hello World!";
    }

    @GetMapping(path = "/short")
    public HelloWorldBean helloWorldShort() {
        return new HelloWorldBean("Hello World (Short)!");
    }

    @GetMapping(path = "/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello World, %s!", name));
    }

}
