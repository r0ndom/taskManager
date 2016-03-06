package com.pb.task.manager.dao;

import com.pb.task.manager.mapper.UserMapper;
import com.pb.task.manager.model.Role;
import com.pb.task.manager.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDao {

    public static final String DEFAULT_PASSWORD = "123123";
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserMapper mapper;

    public void create(User user) {
        String encodedPassword = passwordEncoder.encode(DEFAULT_PASSWORD);
        user.setPassword(encodedPassword);
        mapper.create(user);
    }

    public User getCurrentUser() {
        return mapper.findByLdap(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    public User get(String id) {
        return mapper.get(id);
    }

    public void update(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
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

    public Map<String, String> findAllMap() {
        Map<String, String> map = new HashMap<>();
        List<User> list = mapper.findAll();
        for (User user : list) {
            map.put(user.getLdap(), user.toString());
        }
        return map;
    }

    public List<User> findByRole(Role role) {
        return mapper.findByRole(role);
    }
}