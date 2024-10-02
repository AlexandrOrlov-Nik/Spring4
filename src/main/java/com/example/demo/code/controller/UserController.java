package com.example.demo.code.controller;



import com.example.demo.code.dao.UserDAO;
import com.example.demo.code.models.User;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userDAO.show(id));
        return "table/show";
    }

    @GetMapping("/newUser")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "table/newUser";
    }

    @PostMapping("/users")
    public String createUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "table/newUser";

        // Проверка на уникальность email
        if (userDAO.existsByEmail(user.getEmail())) {
            bindingResult.rejectValue("email", "error.user", "Email already exists");
            return "table/newUser";
        }

        userDAO.save(user);
        return "redirect:/table/v1/users";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userDAO.delete(id);
        return "redirect:/table/v1/users";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") int id, Model model) {
        User user = userDAO.show(id);
        model.addAttribute("user", user);
        return "table/editUser";
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                             @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "table/editUser";

        // Проверка на уникальность email при обновлении
        User existingUser = userDAO.show(id);
        if (!existingUser.getEmail().equals(user.getEmail()) && userDAO.existsByEmail(user.getEmail())) {
            bindingResult.rejectValue("email", "error.user", "Email already exists");
            return "table/editUser";
        }

        userDAO.update(id, user);
        return "redirect:/table/v1/users";
    }
}

