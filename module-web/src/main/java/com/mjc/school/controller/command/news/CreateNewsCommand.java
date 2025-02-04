package com.mjc.school.controller.command.news;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.command.Command;
import com.mjc.school.controller.utils.Utils;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;

import java.util.List;
import java.util.Scanner;

public class CreateNewsCommand implements Command {

    private final BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController;
    private final Scanner input;
    private final Utils utils = new Utils();

    public CreateNewsCommand(BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController,
                             Scanner input) {
        this.newsController = newsController;
        this.input = input;
    }

    @Override
    public void execute() {
        boolean isValid = false;
        NewsDtoRequest request = null;

        while (!isValid) {
            try {
                System.out.println("Enter news title:");
                String title = input.nextLine();
                System.out.println("Enter news content:");
                String content = input.nextLine();
                System.out.println("Enter author id:");
                Long authorId = utils.userNumber(input);
                System.out.println("Enter tag id:");
                Long tagId = utils.userNumber(input);
                request = new NewsDtoRequest(null, title, content, authorId, List.of(tagId));
                isValid = true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(newsController.create(request));
    }
}
