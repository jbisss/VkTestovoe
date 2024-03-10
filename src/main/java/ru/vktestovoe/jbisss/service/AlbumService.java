package ru.vktestovoe.jbisss.service;

import org.springframework.stereotype.Service;
import ru.vktestovoe.jbisss.config.ApplicationConstants;
import ru.vktestovoe.jbisss.dto.AlbumDto;
import ru.vktestovoe.jbisss.service.api.ApiHttpRequestHandlerService;
import ru.vktestovoe.jbisss.service.api.ApiRequestService;

@Service
public class AlbumService extends BaseService<AlbumDto> {

    public AlbumService(ApiRequestService apiRequestService) {
        super(apiRequestService, ApplicationConstants.Url.ALBUMS_URL);
    }
}
