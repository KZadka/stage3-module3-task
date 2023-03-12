package com.mjc.school.controller.menu;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.command.*;
import com.mjc.school.controller.command.author.*;
import com.mjc.school.controller.command.news.*;
import com.mjc.school.controller.command.tag.*;
import com.mjc.school.service.dto.*;
import com.mjc.school.service.exception.ResourceNotFoundException;
import com.mjc.school.service.exception.ValidatorException;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Menu {

    private final BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController;
    private final BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> authorController;
    private final BaseController<TagDtoRequest, TagDtoResponse, Long> tagController;
    private final Invoker invoker;
    Scanner input = new Scanner(System.in);

    public Menu(BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController,
                BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> authorController,
                BaseController<TagDtoRequest , TagDtoResponse, Long> tagController,
                Invoker invoker) {
        this.newsController = newsController;
        this.authorController = authorController;
        this.tagController = tagController;
        this.invoker = invoker;
    }

    public void start() {
        boolean running = true;
        while (running) {
            try {
                menuScreen();
                switch (input.nextLine()) {
                    case "1" -> invoker.setCommand(new ReadAllNewsCommand(newsController));
                    case "2" -> invoker.setCommand(new ReadAllAuthorsCommand(authorController));
                    case "3" -> invoker.setCommand(new ReadAllTagsCommand(tagController));
                    case "4" -> invoker.setCommand(new ReadNewsByIdCommand(newsController, input));
                    case "5" -> invoker.setCommand(new ReadAuthorByIdCommand(authorController, input));
                    case "6" -> invoker.setCommand(new ReadTagByIdCommand(tagController, input));
                    case "7" -> invoker.setCommand(new CreateNewsCommand(newsController, input));
                    case "8" -> invoker.setCommand(new CreateAuthorCommand(authorController, input));
                    case "9" -> invoker.setCommand(new CreateTagCommand(tagController, input));
                    case "10" -> invoker.setCommand(new UpdateNewsCommand(newsController, input));
                    case "11" -> invoker.setCommand(new UpdateAuthorCommand(authorController, input));
                    case "12" -> invoker.setCommand(new UpdateTagCommand(tagController, input));
                    case "13" -> invoker.setCommand(new DeleteNewsCommand(newsController, input));
                    case "14" -> invoker.setCommand(new DeleteAuthorCommand(authorController, input));
                    case "15" -> invoker.setCommand(new DeleteTagCommand(tagController, input));
                    case "0" -> {
                        running = false;
                        System.exit(0);
                    }
                    default -> System.out.println("Wrong input, please try again.");
                }
            } catch (ValidatorException e) {
                System.out.println(e.getMessage() + " Error code: " + e.getErrorCode());
            } catch (ResourceNotFoundException e) {
                System.out.println(e.getMessage() + " Error code: " + e.getErrorCode());
            }
        }
    }

    public void menuScreen() {
        System.out.println("""
                Pick a number for operation:
                1. Get all news
                2. Get all authors
                3. Get all tags
                4. Get news by id
                5. Get author by id
                6. Get tag by id
                7. Create news
                8. Create author
                9. Create tag
                10. Update news by id
                11. Update author by id
                12. Update tag by id
                13. Delete news by id
                14. Delete author by id
                15. Delete tag by id
                0. Exit program
                """);
    }
}
