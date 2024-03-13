package ru.vktestovoe.jbisss.config;

public interface ApplicationConstants {

    String SLASH = "/";
    String REQUEST_PARAM = "?";

    interface Url {

        String JSON_PLACE_HOLDER_URL = "https://jsonplaceholder.typicode.com";
        String API_URL = "/api";
        String POST_URL = "/posts";
        String ALBUMS_URL = "/albums";
        String COMMENTS_URL = "/comments";
        String USERS_URL = "/users";
    }

    interface Exception {

        String REQUEST_FAIL = "Request can't be handled!";
    }

    interface Cash {

        // such values should be injected through config file (.yml)
        Integer CASH_SIZE = 30;
    }
}
