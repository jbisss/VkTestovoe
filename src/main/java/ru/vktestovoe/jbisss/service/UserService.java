package ru.vktestovoe.jbisss.service;

import org.springframework.stereotype.Service;
import ru.vktestovoe.jbisss.config.ApplicationConstants;
import ru.vktestovoe.jbisss.dto.UserDto;
import ru.vktestovoe.jbisss.service.api.ApiHttpRequestHandlerService;

@Service
public class UserService extends BaseService<UserDto> {

    public UserService(ApiHttpRequestHandlerService apiHttpRequestHandlerService) {
        super(apiHttpRequestHandlerService, ApplicationConstants.Url.USERS_URL);
    }

}
