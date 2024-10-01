package com.example.demo.code.dao;

import com.example.demo.code.models.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAO {
    private static int USER_COUNT;
    private final List<User> users;

    {
        users = new ArrayList<>();
        users.add(new User(++USER_COUNT, "Tom", "tom@mail.com"));
        users.add(new User(++USER_COUNT, "Mel","mel@mail.com"));
        users.add(new User(++USER_COUNT, "Sem","sem@mail.com"));
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

    public void update(int id, User updatedUser) {
        User userToBeUpdated = show(id);

        userToBeUpdated.setName(updatedUser.getName());
        userToBeUpdated.setEmail(updatedUser.getEmail());
        }

    public void delete(int id) {
        users.removeIf(user -> user.getId() == id);

    }
}



