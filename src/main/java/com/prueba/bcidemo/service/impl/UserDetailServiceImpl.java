package com.prueba.bcidemo.service.impl;

import com.prueba.bcidemo.model.entity.Users;
import com.prueba.bcidemo.repository.UserRepository;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users userDetails = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User " + username + " doesn't exists!"));

        /*Collection<? extends GrantedAuthority> authorities =
            Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")).stream().collect(Collectors.toSet());*/

        List<SimpleGrantedAuthority> simpleGrantedAuthorityList = Arrays.asList(new SimpleGrantedAuthority(
            "ROLE_ADMIN"));

        return new User(userDetails.getUsername(),userDetails.getPassword(),
            true, true,true,true,simpleGrantedAuthorityList);
    }
}
