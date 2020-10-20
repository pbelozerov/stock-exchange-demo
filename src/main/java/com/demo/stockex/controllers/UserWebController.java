package com.demo.stockex.controllers;

import com.demo.stockex.domain.User;
import com.demo.stockex.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserWebController {
    private final UserService userService;

    @GetMapping({"/", "", "/index"})
    public String getIndexPage() {
        return "redirect:/user/show";
    }


    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/register")
    public String processRegistration(@ModelAttribute @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }

        if (userService.save(user)) {
            return "redirect:/login";
        }

        return "redirect:/register";
    }

    @GetMapping("/user/show")
    public String showUserProfile(Model model, Principal principal) {
        User requestedUser = (User) userService.loadUserByUsername(principal.getName());
        model.addAttribute("requestedUser", requestedUser);
        return "userProfile";
    }
}
