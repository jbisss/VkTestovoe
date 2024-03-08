package ru.vktestovoe.jbisss.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.vktestovoe.jbisss.dto.AlbumDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlbumService extends JsonPlaceHolderService {

    private static final String ALBUMS_URL = JSON_PLACE_HOLDER_URL + "/albums";

    private final RestTemplate restTemplate;

    public List<AlbumDto> getAllAlbums() {
        ParameterizedTypeReference<List<AlbumDto>> responseType = new ParameterizedTypeReference<>() {};
        ResponseEntity<List<AlbumDto>> response = restTemplate.exchange(ALBUMS_URL, HttpMethod.GET, null, responseType);

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException(String.valueOf(response.getStatusCode()));
        }
        return response.getBody();
    }
}
