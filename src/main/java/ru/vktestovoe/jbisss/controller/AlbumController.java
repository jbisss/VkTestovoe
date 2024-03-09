package ru.vktestovoe.jbisss.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vktestovoe.jbisss.config.ApplicationConstants;
import ru.vktestovoe.jbisss.dto.AlbumDto;
import ru.vktestovoe.jbisss.service.AlbumService;

@RestController
@RequestMapping(ApplicationConstants.Url.ALBUMS_URL)
public class AlbumController extends BaseController<AlbumDto> {

    public AlbumController(AlbumService albumService) {
        super(albumService);
    }
}
