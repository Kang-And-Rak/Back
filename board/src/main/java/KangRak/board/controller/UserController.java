package KangRak.board.controller;

import KangRak.board.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping()
public class UserController {

    private final UserService userService;


}
