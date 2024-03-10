package ru.vktestovoe.jbisss.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.vktestovoe.jbisss.config.ApplicationConstants;
import ru.vktestovoe.jbisss.dto.AlbumDto;

@Service
public class AlbumService extends BaseService<AlbumDto> {

    public AlbumService(RequestHandlerService requestHandlerService, RestTemplate restTemplate) {
        super(requestHandlerService, restTemplate, ApplicationConstants.Url.ALBUMS_URL);
    }
}
