package io.silva.bookshop.controller;

import io.silva.bookshop.model.User;
import io.silva.bookshop.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    UserService userService;

    @PostMapping
    public void postUser(@RequestBody User user){
        userService.saveUser(user);
    }

    @GetMapping
    public List<User> searchAllUsers(@RequestParam(required = false) String name, @RequestParam(required = false) String email){
        return userService.searchUsers(name, email);
    }

    @PutMapping("{email}")
    public void updateUserByEmail(@RequestBody User user, @PathVariable String email){
        userService.updateUser( user, email);
    }

    @DeleteMapping("{email}")
    public void deleteUserByEmail(@PathVariable String email){
        userService.deleteUser(email);
    }
}
