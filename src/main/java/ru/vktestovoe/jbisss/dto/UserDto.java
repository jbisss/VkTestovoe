package ru.vktestovoe.jbisss.dto;

import lombok.Data;

@Data
public class UserDto {

    @Data
    public static class Address {

        @Data
        public static class Geo {

            private String lat;
            private String lng;
        }

        private String street;
        private String suite;
        private String city;
        private String zipcode;
        private Geo geo;
    }

    private String id;
    private String name;
    private String userName;
    private String email;
    private Address address;
}
