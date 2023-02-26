package com.team.project.auth.security;

import com.team.project.user.entity.User;
import com.team.project.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email){
        User user = userService.getByEmail(email).orElseThrow(() -> new UsernameNotFoundException("E-mail not found"));;
        return UserDetailsImpl.build(user);
    }
}
