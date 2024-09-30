package com.example.demo.code.controller;



import com.example.demo.code.dao.UserDAO;
import com.example.demo.code.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/table/v1")
public class UserController {

    private final UserDAO userDAO;

    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    @GetMapping("/users")
    public String userTable(Model model) {
        model.addAttribute("users", userDAO.userTable());
        return "table/users";
    }

    @GetMapping("/usersTableSave")
    public String userTableSave() {
        return "table/usersTableSave";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("users", userDAO.show(id));
        return "table/show";

    }

    @GetMapping("/newUser")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "table/newUser";
    }


    public String createUser(@ModelAttribute ("user")User user) {
        userDAO.save(user);
        return "redirect:/users";
    }



}
