package fontys.sem3.hpfapi.controller;

import fontys.sem3.hpfapi.business.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserControler {
    private final UserService userService;


}