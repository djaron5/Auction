package com.example.Auction.controllers;

import com.example.Auction.domain.Role;
import com.example.Auction.domain.User;
import com.example.Auction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/user")
//@PreAuthorize("hasAnyRole('ADMIN')")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public String userList(Model model) {
        model.addAttribute("users", userService.findAll());
        return "userList";
    }

    @GetMapping("{user}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> roles,
            @RequestParam("userId") User user) {
        userService.saveUser(user, username, roles);

        return "redirect:/user";
    }

    @GetMapping("profile")
    public String getProfile(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());
        return "profile";
    }

    @PostMapping("profile")
    public String updateProfile(Model model,
                                @AuthenticationPrincipal User user,
                                @RequestParam String password,
                                @RequestParam String email) {
        userService.updateProfile(user, password, email);

        return "redirect:/user/profile";
    }

    @PostMapping("profile/changePassword")
    public String changePassword(Model model,
                                 @AuthenticationPrincipal User user,
                                 @RequestParam(name = "oldPassword") String oldPassword,
                                 @RequestParam(name = "password2") String password2,
                                 @RequestParam(name = "password3") String password3) {
        String errorMessage = null;
        if (!password2.equals(password3)) {
            errorMessage = "Passwords are different";
            model.addAttribute("newPasswordError", errorMessage);
        } else if (password2.isEmpty() || password3.isEmpty()) {
            errorMessage = "Passwords con not be empty";
            model.addAttribute("newPasswordError", errorMessage);
        } else if (!userService.isPasswordsEquals(user, oldPassword)) {
            errorMessage = "Incorrect password was entered";
            model.addAttribute("passwordError", errorMessage);
        } else if (!userService.isPasswordsEquals(user, password2)) {
            userService.changePassword(user, password2);

        }
        if (errorMessage != null)
            model.addAttribute("errorMessage", errorMessage);
        else model.addAttribute("successMessage", "Password was successfully changed");
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());
        return "profile";
    }
}
