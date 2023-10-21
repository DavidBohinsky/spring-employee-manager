package net.javaguides.springboot.security;

import net.javaguides.springboot.model.UserM;
import net.javaguides.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserM userM = userRepository.findByUsername(username);
        if (userM == null) {
            throw new UsernameNotFoundException(username);
        }
        UserDetails user = User.withUsername(userM.getUsername()).password(userM.getPassword()).authorities("USER").build(); // heslo je siftovane Bcrypt, https://bcrypt-generator.com/
        // napr. slovo heslo123 reprezentuje $2a$12$hM8V5ss.KaaT5PtP8vScSeIQ5GwEvwNteuX1vxigBPTgIcVo88IuC
        return user;
    }
}
