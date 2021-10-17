package com.aloha.learn.spring.ws.rest.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @GetMapping("v1/person")
    public PersonV1 personV1Uri() {
        return new PersonV1("Bob Chan");
    }

    @GetMapping("v2/person")
    public PersonV2 personV2Uri() {
        return new PersonV2(new Name("Bob", "Chan"));
    }

    @GetMapping(value = "/person/param", params = "version=1")
    public PersonV1 personV1Param() {
        return new PersonV1("Bob Chan");
    }

    @GetMapping(value = "/person/param", params = "version=2")
    public PersonV2 personV2Param() {
        return new PersonV2(new Name("Bob", "Chan"));
    }

    @GetMapping(value = "/person/header", headers = "X-API-VERSION=1")
    public PersonV1 personV1Header() {
        return new PersonV1("Bob Chan");
    }

    @GetMapping(value = "/person/header", headers = "X-API-VERSION=2")
    public PersonV2 personV2Header() {
        return new PersonV2(new Name("Bob", "Chan"));
    }

    @GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v1+json")
    public PersonV1 personV1Produces() {
        return new PersonV1("Bob Chan");
    }

    @GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v2+json")
    public PersonV2 personV2Produces() {
        return new PersonV2(new Name("Bob", "Chan"));
    }

}
