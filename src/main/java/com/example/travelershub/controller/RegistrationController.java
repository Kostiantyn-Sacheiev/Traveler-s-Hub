package com.example.travelershub.controller;

import com.example.travelershub.config.PasswordEncoderProvider;
import com.example.travelershub.model.Role;
import com.example.travelershub.model.User;
import com.example.travelershub.model.enumfolder.RoleName;
import com.example.travelershub.service.UserService;
import java.util.Collections;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    private final UserService userService;

    private final PasswordEncoderProvider passwordEncoder;

    public RegistrationController(UserService userService, PasswordEncoderProvider passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(String name, String username, String password) {
        User user = new User();
        user.setFirstName(name);
        user.setLastName(username);
        user.setPassword(passwordEncoder.getPasswordEncoder().encode(password));
        user.setRoles(Collections.singleton(new Role(RoleName.USER)));

        userService.save(user);

        return "redirect:/login";
    }


    //    @GetMapping("/registration")
    //    public String showRegistrationForm(Model model) {
    //        model.addAttribute("user", new User());
    //        return "registration";
    //    }
    //
    //    @PostMapping("/registration")
    //    public String processRegistrationForm(@ModelAttribute("user") User user) {
    //        userService.save(user);
    //        return "redirect:/login";
    //    }

}
