package com.crms.hrms_backend.Service.ServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.crms.hrms_backend.Repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository dbUserReadLoginRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {
            Map<String, Object> userLogin = this.dbUserReadLoginRepo.findByUsername(username);
            // if (userLogin.get("enabled").toString().equals("false")) {
            //     throw new UsernameNotFoundException("User is Disable");
            // }
            List<SimpleGrantedAuthority> roles = Arrays
                    .asList(new SimpleGrantedAuthority(userLogin.get("role_name").toString()));
            return new User(userLogin.get("username").toString(), userLogin.get("password").toString(), roles);
        } catch (UsernameNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new UsernameNotFoundException("Error while loading user by username");
        }

    }

}
