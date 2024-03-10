package ru.vktestovoe.jbisss.service;

import org.springframework.stereotype.Service;
import ru.vktestovoe.jbisss.config.ApplicationConstants;
import ru.vktestovoe.jbisss.dto.UserDto;

@Service
public class UserService extends BaseService<UserDto> {

    public UserService(RequestHandlerService requestHandlerService) {
        super(requestHandlerService, ApplicationConstants.Url.USERS_URL);
    }

}
