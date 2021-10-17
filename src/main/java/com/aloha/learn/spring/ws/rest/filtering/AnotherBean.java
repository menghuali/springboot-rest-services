package com.aloha.learn.spring.ws.rest.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Sample of dynamic property filtering
 */
@JsonFilter("AnotherBeanFilter")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AnotherBean {

    private String field1;
    private String field2;
    private String field3;
}