package KangRak.board.controller;

import KangRak.board.service.CommentService;
import KangRak.board.service.PostService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
}
