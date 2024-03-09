package ru.vktestovoe.jbisss.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.vktestovoe.jbisss.config.ApplicationConstants;
import ru.vktestovoe.jbisss.dto.UserDto;

@Service
public class UserService extends BaseService<UserDto> {

    public UserService(RestTemplate restTemplate) {
        super(restTemplate, ApplicationConstants.Url.USERS_URL);
    }

}
