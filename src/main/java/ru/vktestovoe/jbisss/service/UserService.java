package ru.vktestovoe.jbisss.service;

import org.springframework.stereotype.Service;
import ru.vktestovoe.jbisss.config.ApplicationConstants;
import ru.vktestovoe.jbisss.dto.UserDto;
import ru.vktestovoe.jbisss.service.api.ApiHttpRequestHandlerService;
import ru.vktestovoe.jbisss.service.api.ApiRequestService;

@Service
public class UserService extends BaseService<UserDto> {

    public UserService(ApiRequestService apiRequestService) {
        super(apiRequestService, ApplicationConstants.Url.USERS_URL);
    }

}
