package ru.vktestovoe.jbisss.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.vktestovoe.jbisss.service.RestCrudService;

import java.util.List;

@RequiredArgsConstructor
abstract public class ParameterizedController<DTO> {

    private final RestCrudService<DTO> restCrudService;

    @GetMapping
    public List<DTO> getList() {
        return restCrudService.getList();
    }

    @GetMapping("/{id}")
    public DTO get(@PathVariable String id) {
        return restCrudService.get(id);
    }

    @PostMapping
    public DTO post(@RequestBody DTO dto) {
        return restCrudService.post(dto);
    }

    @PutMapping("/{id}")
    public DTO put(@PathVariable String id, @RequestBody DTO dto) {
        return restCrudService.put(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        restCrudService.delete(id);
    }
}
