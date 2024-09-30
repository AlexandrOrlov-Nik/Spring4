package com.example.demo.code.dao;

import com.example.demo.code.models.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAO {
    private static int USER_COUNT;
    private List<User> users;

    {
        users = new ArrayList<>();
        users.add(new User(++USER_COUNT, "Tom"));
        users.add(new User(++USER_COUNT, "Mel"));
        users.add(new User(++USER_COUNT, "Sem"));
    }
    public List<User> userTable() {
        return users;
    }
    public User show(int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }

    public void save(User user) {
        user.setId(++USER_COUNT);
        users.add(user);
    }
}
