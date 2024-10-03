package com.example.demo.code.dao;

import com.example.demo.code.models.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class UserDAO {

    @PersistenceContext
    private EntityManager entityManager;




    @Transactional(readOnly = true)
    public List<User> userTable() {
        List<User> resultList = entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();

        return resultList;

    }

    @Transactional(readOnly = true)
    public User show(int id) {
        return entityManager.find(User.class, id);

    }

    @Transactional
    public void save(User user) {
        entityManager.persist(user);

    }

    @Transactional
    public void update(int id, User updatedUser) {
        User user = entityManager.find(User.class, id);
        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        entityManager.merge(user);

    }

    @Transactional
    public void delete(int id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);

    }

    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        Long count = entityManager.createQuery(
                        "SELECT COUNT(u) FROM User u WHERE u.email = :email", Long.class)
                .setParameter("email", email)
                .getSingleResult();
        return count > 0;
    }

}



