package com.pb.task.manager.mapper;


import com.pb.task.manager.model.Role;
import com.pb.task.manager.model.User;

import java.util.List;

public interface UserMapper {
    void create(User user);
    User get(String id);
    void update(User user);
    void delete(String id);
    User findByLdap(String ldap);
    List<User> findAll();
    List<User> findByRole(Role role);
}
