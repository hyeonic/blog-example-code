package me.hyeonic.springopenfeign.controller;

import lombok.RequiredArgsConstructor;
import me.hyeonic.springopenfeign.domain.User;
import me.hyeonic.springopenfeign.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class TestController {

    private final UserService userService;

    @GetMapping("/test1")
    public List<User> test1() {
        return userService.getUsers();
    }
}