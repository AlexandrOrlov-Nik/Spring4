package com.example.demo.code.dao;

import com.example.demo.code.models.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class UserDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<User> userTable() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select p from User p", User.class)
               .getResultList();

    }

    @Transactional(readOnly = true)
    public User show(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, id);
    }

    @Transactional
    public void save(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(user);

    }

    @Transactional
    public void update(int id, User updatedUser) {
        Session session = sessionFactory.getCurrentSession();
        User userToBeUpdate = session.get(User.class, id);
        userToBeUpdate.setName(updatedUser.getName());
        userToBeUpdate.setEmail(updatedUser.getEmail());
        session.update(userToBeUpdate);

    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        User userToBeDeleted = session.get(User.class, id);
        session.delete(userToBeDeleted);

    }

    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        Long count = (Long) session.createQuery("SELECT COUNT(u) FROM User u WHERE u.email = :email")
                .setParameter("email", email)
                .uniqueResult();
        return count != null && count > 0;
    }

}



