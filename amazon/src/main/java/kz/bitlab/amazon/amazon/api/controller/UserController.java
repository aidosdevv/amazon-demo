package kz.bitlab.amazon.amazon.api.controller;

import kz.bitlab.amazon.amazon.models.User;
import kz.bitlab.amazon.amazon.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @Autowired
    private  UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/sign-up")
    public String registration(){
        return "/sign-up";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/sign-up")
    public String createUser(User user) {
        userService.createUser(user);
        return "redirect:/login";
    }

    @GetMapping("/hello")
    public String securityUrl() {
        return "hello";
    }

    @PostMapping("/entering")
    @PreAuthorize("isAnonymous()")
    public String login(@RequestParam (name = "user_email") String email,
                        @RequestParam (name = "user_password") String password) {
        Boolean currentUser = userService.login(email, password);
        if(currentUser) {
            return "redirect:/";
        }
        return "redirect:/login?error";
    }
}
