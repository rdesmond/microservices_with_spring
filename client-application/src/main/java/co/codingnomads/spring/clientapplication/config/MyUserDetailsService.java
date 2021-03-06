package co.codingnomads.spring.clientapplication.config;

import co.codingnomads.spring.clientapplication.model.User;
import co.codingnomads.spring.clientapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userService.getUser(username);
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));
        if (user != null) {
            return new org.springframework.security.core.userdetails.User(username, user.getPassword(),
                    true, true, true, true, authorities);
        } else {
            return null;
        }
    }
}
