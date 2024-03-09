package ru.vktestovoe.jbisss.dto;

import lombok.Data;

@Data
public class CommentDto {

    String postId;
    String id;
    String name;
    String email;
    String body;
}
