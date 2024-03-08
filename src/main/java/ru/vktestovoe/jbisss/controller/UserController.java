package ru.vktestovoe.jbisss.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vktestovoe.jbisss.dto.UserDto;
import ru.vktestovoe.jbisss.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController extends BaseController {

    private final UserService userService;

    @GetMapping("/users")
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }
}
