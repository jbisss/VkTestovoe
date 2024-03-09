package ru.vktestovoe.jbisss.service;

import java.util.List;

public interface RestCrudService<DTO> {

    List<DTO> getList();
    DTO get(String id);
    DTO post(DTO dto);
    DTO put(String id, DTO dto);
    String delete(String id);
}
