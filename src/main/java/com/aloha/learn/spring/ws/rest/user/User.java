package com.aloha.learn.spring.ws.rest.user;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User {

    private Integer id;

    @Size(min = 2, max = 200)
    private String name;

    @Past
    private Date dob;

}
