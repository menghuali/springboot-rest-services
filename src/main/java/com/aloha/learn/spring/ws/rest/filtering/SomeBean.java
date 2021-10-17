package com.aloha.learn.spring.ws.rest.filtering;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Sample of static property filtering
 */
@JsonIgnoreProperties("field2")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SomeBean {

    private String field1;
    private String field2;

    @JsonIgnore
    private String field3;

}
