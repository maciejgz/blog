package pl.mg.blog.config.security;

import org.springframework.context.annotation.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.mg.blog.user.UserDto;
import pl.mg.blog.user.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserRepositoryDetailsService implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        UserDto user = UserRepository.USERS.get(username);
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user " + username);
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}
