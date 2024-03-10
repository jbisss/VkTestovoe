package ru.vktestovoe.jbisss.service;

import org.springframework.stereotype.Service;
import ru.vktestovoe.jbisss.config.ApplicationConstants;
import ru.vktestovoe.jbisss.dto.AlbumDto;

@Service
public class AlbumService extends BaseService<AlbumDto> {

    public AlbumService(RequestHandlerService requestHandlerService) {
        super(requestHandlerService, ApplicationConstants.Url.ALBUMS_URL);
    }
}
