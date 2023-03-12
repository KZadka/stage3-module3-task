package com.mjc.school.controller.command.tag;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.command.Command;
import com.mjc.school.controller.utils.Utils;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;

import java.util.Scanner;

public class ReadTagByIdCommand implements Command {

    private final BaseController<TagDtoRequest, TagDtoResponse, Long> tagController;
    private final Scanner input;
    private final Utils utils = new Utils();

    public ReadTagByIdCommand(BaseController<TagDtoRequest, TagDtoResponse, Long> tagController,
                              Scanner input) {
        this.tagController = tagController;
        this.input = input;
    }

    @Override
    public void execute() {
        System.out.println("Enter tag id:");
        System.out.println(tagController.readById(utils.userNumber(input)));
    }
}
