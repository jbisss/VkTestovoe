package ru.vktestovoe.jbisss.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vktestovoe.jbisss.config.ApplicationConstants;
import ru.vktestovoe.jbisss.dto.UserDto;
import ru.vktestovoe.jbisss.service.UserService;

@RestController
@RequestMapping(ApplicationConstants.Url.USERS_URL)
public class UserController extends ParameterizedController<UserDto> {

    public UserController(UserService userService) {
        super(userService);
    }
}
