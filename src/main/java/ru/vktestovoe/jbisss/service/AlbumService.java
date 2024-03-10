package ru.vktestovoe.jbisss.service;

import org.springframework.stereotype.Service;
import ru.vktestovoe.jbisss.config.ApplicationConstants;
import ru.vktestovoe.jbisss.dto.AlbumDto;
import ru.vktestovoe.jbisss.service.api.ApiHttpRequestHandlerService;

@Service
public class AlbumService extends BaseService<AlbumDto> {

    public AlbumService(ApiHttpRequestHandlerService apiHttpRequestHandlerService) {
        super(apiHttpRequestHandlerService, ApplicationConstants.Url.ALBUMS_URL);
    }
}
