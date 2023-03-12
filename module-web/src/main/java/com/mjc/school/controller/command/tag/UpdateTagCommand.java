package com.mjc.school.controller.command.tag;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.command.Command;
import com.mjc.school.controller.utils.Utils;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;

import java.util.Scanner;

public class UpdateTagCommand implements Command {

    private final BaseController<TagDtoRequest, TagDtoResponse, Long> tagController;
    private final Scanner input;
    private final Utils utils = new Utils();

    public UpdateTagCommand(BaseController<TagDtoRequest, TagDtoResponse, Long> tagController,
                            Scanner input) {
        this.tagController = tagController;
        this.input = input;
    }

    @Override
    public void execute() {
        boolean isValid = false;
        TagDtoRequest request = null;

        while (!isValid) {
            try {
                System.out.println("Enter id od tag to update:");
                Long tagId = utils.userNumber(input);
                System.out.println("Enter new tag name:");
                String name = input.nextLine();
                request = new TagDtoRequest(tagId, name);
                isValid = true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(tagController.update(request));
    }
}
