package com.aloha.learn.spring.ws.rest.filtering;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Sample of filtering response properties
 */
@RequestMapping("filtering")
@RestController
public class FilteringController {

    @GetMapping("/static/bean")
    public SomeBean staticFiltering() {
        return new SomeBean("value1", "value2", "value3");
    }

    @GetMapping("/static/beans")
    public List<SomeBean> staticFilteringList() {
        return Arrays.asList(new SomeBean("value1", "value2", "value3"), new SomeBean("valueA", "valueB", "valueC"));
    }

    @GetMapping("/dynamic-1")
    public MappingJacksonValue dynamicFiltering1() {
        return dynamicFiltering(new AnotherBean("value1", "value2", "value3"), "field1", "field2");
    }

    @GetMapping("/dynamic-2")
    public MappingJacksonValue dynamicFiltering2() {
        return dynamicFiltering(
                Arrays.asList(new SomeBean("value1", "value2", "value3"), new SomeBean("valueA", "valueB", "valueC")),
                "field3");
    }

    private MappingJacksonValue dynamicFiltering(Object value, String... propertiesToKeep) {
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(propertiesToKeep);
        FilterProvider filters = new SimpleFilterProvider().addFilter("AnotherBeanFilter", filter);
        MappingJacksonValue mjv = new MappingJacksonValue(value);
        mjv.setFilters(filters);
        return mjv;
    }

}
