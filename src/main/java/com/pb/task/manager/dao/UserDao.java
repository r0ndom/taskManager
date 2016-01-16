package com.pb.task.manager.dao;

import com.pb.task.manager.mapper.UserMapper;
import com.pb.task.manager.model.Role;
import com.pb.task.manager.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {

    public static final String DEFAULT_PASSWORD = "123123";
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserMapper mapper;

    public void createDeveloper(User user) {
        create(user, Role.ROLE_DEVELOPER);
    }

    public void createManager(User user) { create(user, Role.ROLE_MANAGER); }

    public void create(User user, Role role) {
        String encodedPassword = passwordEncoder.encode(DEFAULT_PASSWORD);
        user.setPassword(encodedPassword);
        user.setRole(role);
        mapper.create(user);
    }

    public User getCurrentUser() {
        return mapper.findByLdap(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    public User get(String id) {
        return mapper.get(id);
    }

    public void update(User user) {
        mapper.update(user);
    }

    public void delete(String id) {
        mapper.delete(id);
    }

    public User findByLdap(String ldap) {
        return mapper.findByLdap(ldap);
    }

    public List<User> findAll() {
        return mapper.findAll();
    }

    public List<User> findByRole(Role role) {
        return mapper.findByRole(role);
    }
}