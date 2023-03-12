package com.mjc.school.controller.command.tag;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.command.Command;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;

public class ReadAllTagsCommand implements Command {

    private final BaseController<TagDtoRequest, TagDtoResponse, Long> tagController;

    public ReadAllTagsCommand(BaseController<TagDtoRequest, TagDtoResponse, Long> tagController) {
        this.tagController = tagController;
    }

    @Override
    public void execute() {
        tagController.readAll().forEach(System.out::println);
    }
}
