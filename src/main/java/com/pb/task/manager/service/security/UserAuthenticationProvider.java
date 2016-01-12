package com.pb.task.manager.service.security;

import com.pb.task.manager.dao.UserDao;
import com.pb.task.manager.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDao userDao;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(final Authentication authentication) {
        final String login = authentication.getName();
        final String password = authentication.getCredentials().toString();

        if (login == null || login.trim().isEmpty()) {
            throw new BadCredentialsException("Логин не может быть пустым");
        }

        if (password == null || password.trim().isEmpty()) {
            throw new BadCredentialsException("Пароль не может быть пустым");
        }

        final User user = userDao.findByLdap(login);

        if (user == null) {
            throw new BadCredentialsException("Пользователь не найден");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Неверный пароль");
        }

        final Collection<? extends GrantedAuthority> authorities
                = user.getAuthorities();

        return new UsernamePasswordAuthenticationToken(user, password,
                authorities);
    }

    @Override
    public boolean supports(final Class<?> aClass) {
        return true;
    }
}