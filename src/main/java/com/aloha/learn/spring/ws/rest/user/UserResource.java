package com.aloha.learn.spring.ws.rest.user;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * UserResource
 */
@RestController
@RequestMapping("/users")
public class UserResource {

    private UserDao userDao;

    @GetMapping
    public List<User> retrieveAllUsers() {
        return userDao.findAll();
    }

    @GetMapping("/{id}")
    public User retrieveUser(@PathVariable("id") Integer id) {
        User user = userDao.findOne(id);
        if (user != null)
            return user;
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        User savedUser = userDao.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();

    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

}