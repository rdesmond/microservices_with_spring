package co.codingnomads.spring.clientapplication.controller;

import co.codingnomads.spring.clientapplication.model.User;
import co.codingnomads.spring.clientapplication.service.ItemService;
import co.codingnomads.spring.clientapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class ViewController {

    @Autowired
    ItemService itemService;

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String displayHomePage() {
        return "index";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/items")
    public String displayItems(Model model, Principal principal) {
        User user = userService.getUser(principal.getName());
        model.addAttribute("userId", user.getId());
        model.addAttribute("items", itemService.getAllItems());
        return "item-list";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute(new User());
        return "register-form";
    }

    @PostMapping("/register")
    public String submitRegisterForm(@ModelAttribute("user") User user, Model model) {
        User createdUser = userService.registerNewUser(user);
        if (createdUser == null) {
            return "register-failure";
        }
        model.addAttribute("user", createdUser);
        return "register-success";
    }
}
