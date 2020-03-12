package com.example.formsubmission;

import org.dom4j.swing.XMLTableModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller("/")
public class UserController {
    private final CustomUserDetailsManager customUserDetailsManager;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(CustomUserDetailsManager customUserDetailsManager) {
        this.customUserDetailsManager = customUserDetailsManager;
    }

    @GetMapping
    public String getUsers(Model model){
        model.addAttribute("users", customUserDetailsManager.loadAllUsers());
        return "index";
    }

    @PostMapping
    public String updateUser(Model model, UserDto userDto){
        customUserDetailsManager.updateDto(userDto);
        model.addAttribute("users", customUserDetailsManager.loadAllUsers());
        return "index";
    }
}
