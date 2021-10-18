package com.aloha.learn.spring.ws.rest.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * UserResource, Sample of REST contoller
 */
@Tag(name = "user", description = "the user API")
@RestController
@RequestMapping("/jpa/users")
public class UserJpaResource {

    private UserRepository userDao;
    private PostRepository postDao;

    @Operation(summary = "Retrieve all users", tags = { "user" })
    @GetMapping
    public List<User> retrieveAllUsers() {
        return userDao.findAll();
    }

    @Operation(summary = "Retrieve an user by ID", tags = { "user" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)),
                    @Content(mediaType = "application/xml", schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid user ID supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content) })
    @GetMapping("/{id}")
    public EntityModel<User> retrieveUser(
            @Parameter(description = "user ID", required = true) @PathVariable("id") Integer id) {
        Optional<User> user = userDao.findById(id);
        if (user.isEmpty())
            throw new UserNotFoundException("id=" + id);

        // Adding HATEOAS link to the resource
        EntityModel<User> entityModel = EntityModel.of(user.get());
        WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).retrieveUser(id));
        entityModel.add(linkToUsers.withRel("all-users"));

        return entityModel;
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = userDao.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();

    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Integer id) {
        userDao.deleteById(id);
    }

    @GetMapping("/{id}/posts")
    public List<Post> getPosts(@PathVariable("id") Integer userId) {
        Optional<User> user = userDao.findById(userId);
        if (user.isEmpty())
            throw new UserNotFoundException("id=" + userId);

        return user.get().getPost();
    }

    @PostMapping("/{id}/posts")
    public ResponseEntity<Object> createPost(@PathVariable("id") Integer userId, @Valid @RequestBody Post post) {
        Optional<User> user = userDao.findById(userId);
        if (user.isEmpty())
            throw new UserNotFoundException("id=" + userId);
        post.setUser(user.get());
        Post savedPost = postDao.save(post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}/posts").buildAndExpand(savedPost.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @Autowired
    public void setUserDao(UserRepository userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setPostDao(PostRepository postDao) {
        this.postDao = postDao;
    }

}