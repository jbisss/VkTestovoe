package ru.vktestovoe.jbisss.dto;

import lombok.Data;

@Data
public class PostDto {

    private String userId;
    private String id;
    private String title;
    private String body;
}
