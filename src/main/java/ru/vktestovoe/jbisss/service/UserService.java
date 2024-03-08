package ru.vktestovoe.jbisss.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.vktestovoe.jbisss.dto.UserDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService extends JsonPlaceHolderService{

    private static final String USERS_URL = JSON_PLACE_HOLDER_URL + "/users";

    private final RestTemplate restTemplate;

    public List<UserDto> getUsers() {
        ParameterizedTypeReference<List<UserDto>> responseType = new ParameterizedTypeReference<>() {};
        ResponseEntity<List<UserDto>> response = restTemplate.exchange(USERS_URL, HttpMethod.GET, null, responseType);

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException(String.valueOf(response.getStatusCode()));
        }
        return response.getBody();
    }
}
