package com.mjc.school.controller.implementation;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TagController implements BaseController<TagDtoRequest, TagDtoResponse, Long> {

    private final BaseService<TagDtoRequest, TagDtoResponse, Long> tagService;

    @Autowired
    public TagController(BaseService<TagDtoRequest, TagDtoResponse, Long> tagService) {
        this.tagService = tagService;
    }

    @Override
    public List<TagDtoResponse> readAll() {
        return tagService.readAll();
    }

    @Override
    public TagDtoResponse readById(Long id) {
        return tagService.readById(id);
    }

    @Override
    public TagDtoResponse create(TagDtoRequest createRequest) {
        return tagService.create(createRequest);
    }

    @Override
    public TagDtoResponse update(TagDtoRequest updateRequest) {
        return tagService.update(updateRequest);
    }

    @Override
    public boolean deleteById(Long id) {
        return tagService.deleteById(id);
    }
}
