package me.hyeonic.springsecuritysocialjwt.service;

import lombok.RequiredArgsConstructor;
import me.hyeonic.springsecuritysocialjwt.domain.user.User;
import me.hyeonic.springsecuritysocialjwt.domain.user.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .map(user -> createUserDetails(user))
                .orElseThrow(() -> new UsernameNotFoundException(username + " 존재하지 않는 username 입니다."));
    }

    private UserDetails createUserDetails(User user) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getRole().toString());

        return new org.springframework.security.core.userdetails.User(
                String.valueOf(user.getId()),
                "",
                Collections.singleton(grantedAuthority)
        );
    }
}