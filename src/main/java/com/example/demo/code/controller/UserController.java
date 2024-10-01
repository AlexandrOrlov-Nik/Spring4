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
    public String createUser(@ModelAttribute("user") User user) {
        userDAO.save(user);
        return "redirect:/table/v1/users";
    }

    // Удаление пользователя
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userDAO.delete(id);
        return "redirect:/table/v1/users"; // Перенаправляем на страницу со списком пользователей
    }

    // Отображение формы для изменения пользователя
    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") int id, Model model) {
        User user = userDAO.show(id);
        model.addAttribute("user", user);
        return "table/editUser"; // Название страницы для редактирования
    }

    // Обработка изменения пользователя
    @PostMapping("/edit")
    public String updateUser(@ModelAttribute("user") User user) {
        userDAO.update(user);
        return "redirect:/table/v1/users"; // Перенаправляем на страницу со списком пользователей
    }


}
