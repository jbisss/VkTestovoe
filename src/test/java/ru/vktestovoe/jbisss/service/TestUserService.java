package ru.vktestovoe.jbisss.service;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.vktestovoe.jbisss.dto.UserDto;
import ru.vktestovoe.jbisss.service.config.TestConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestUserService {

    private static final AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(TestConfig.class);

    private static final UserService userService = appContext.getBean(UserService.class);

    @Test
    public void testGetUserList() {
        List<UserDto> users = userService.getList();

        assertNotNull(users);
    }

    @Test
    public void testGetUser() {
        UserDto user = userService.get("1");

        assertNotNull(user);
    }

    @Test
    public void testPostUser() {
        UserDto.Address.Geo geo = new UserDto.Address.Geo();
        geo.setLat("123");
        geo.setLng("123");

        UserDto.Address address = new UserDto.Address();
        address.setCity("Test city");
        address.setStreet("Test street");
        address.setSuite("Test suite");
        address.setZipcode("Test zipcode");
        address.setGeo(geo);

        UserDto testUserDto = new UserDto();
        testUserDto.setId("11");
        testUserDto.setUserName("Test userName");
        testUserDto.setAddress(address);
        testUserDto.setEmail("Test email");
        testUserDto.setName("Test name");

        UserDto postedUser = userService.post(testUserDto);

        assertEquals(testUserDto, postedUser);
    }

    @Test
    public void testPutUser() {
        UserDto.Address.Geo geo = new UserDto.Address.Geo();
        geo.setLat("123");
        geo.setLng("123");

        UserDto.Address address = new UserDto.Address();
        address.setCity("Test city");
        address.setStreet("Test street");
        address.setSuite("Test suite");
        address.setZipcode("Test zipcode");
        address.setGeo(geo);

        UserDto testUserDto = new UserDto();
        testUserDto.setId("1");
        testUserDto.setUserName("Test userName");
        testUserDto.setAddress(address);
        testUserDto.setEmail("Test email");
        testUserDto.setName("Test name");

        UserDto putUser = userService.put("1", testUserDto);

        assertEquals(testUserDto, putUser);
    }

    @Test
    public void testDeleteUser() {
        String status = userService.delete("1");

        assertEquals("200 OK", status);
    }
}
