package ru.vktestovoe.jbisss.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vktestovoe.jbisss.dto.AlbumDto;
import ru.vktestovoe.jbisss.service.AlbumService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AlbumController extends BaseController {

    private final AlbumService albumService;

    @GetMapping("/albums")
    public List<AlbumDto> getAlbums() {
        return albumService.getAllAlbums();
    }
}
