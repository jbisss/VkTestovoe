package ru.vktestovoe.jbisss.service;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.vktestovoe.jbisss.dto.AlbumDto;
import ru.vktestovoe.jbisss.service.config.TestConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestAlbumService {

    private static final AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(TestConfig.class);

    private static final AlbumService albumService = appContext.getBean(AlbumService.class);

    @Test
    public void testGetAlbumList() {
        List<AlbumDto> albums = albumService.getList();

        assertNotNull(albums);
    }

    @Test
    public void testGetAlbum() {
        AlbumDto album = albumService.get("1");

        assertNotNull(album);
    }

    @Test
    public void testPostAlbum() {
        AlbumDto testAlbumDto = new AlbumDto();
        testAlbumDto.setId("101");
        testAlbumDto.setTitle("Test title");
        testAlbumDto.setUserId("2");

        AlbumDto postedAlbum = albumService.post(testAlbumDto);

        assertEquals(testAlbumDto, postedAlbum);
    }

    @Test
    public void testPutAlbum() {
        AlbumDto testAlbumDto = new AlbumDto();
        testAlbumDto.setId("1");
        testAlbumDto.setTitle("Test title");
        testAlbumDto.setUserId("2");

        AlbumDto putAlbum = albumService.put("1", testAlbumDto);

        assertEquals(testAlbumDto, putAlbum);
    }

    @Test
    public void testDeleteAlbum() {
        String status = albumService.delete("1");

        assertEquals("200 OK", status);
    }
}
