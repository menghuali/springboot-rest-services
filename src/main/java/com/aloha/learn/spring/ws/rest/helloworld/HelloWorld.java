package com.aloha.learn.spring.ws.rest.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello-world")
public class HelloWorld {

    @Autowired
    private MessageSource messageSource;

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

    /**
     * Sample of i18n. Locale is passed via parameter
     * 
     * @param locale locale
     * @return localized message
     */
    @RequestMapping(method = RequestMethod.GET, path = "/i18n/param")
    public String helloWorldI18n_Parameter(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        return messageSource.getMessage("hello.world", null, "Hello World", locale);
    }

    /**
     * Sample if i18n. Get locale from LocaleContextHolder
     * 
     * @return localized message
     */
    @RequestMapping(method = RequestMethod.GET, path = "/i18n")
    public String helloWorldI18n() {
        return messageSource.getMessage("hello.world", null, "Hello World", LocaleContextHolder.getLocale());
    }

}
