package com.crms.hrms_backend.Service.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.crms.hrms_backend.Repository.UserRepository;
import com.crms.hrms_backend.Service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Map<String, Object>> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public Map<String, Object> getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public void createUser(Map<String, Object> user) {
        String encodedPassword = passwordEncoder.encode((CharSequence) user.get("password"));
        user.put("password", encodedPassword);
        userRepository.createUser(user);
    }

    @Override
    public void updateUser(String username, Map<String, Object> user) {
        userRepository.updateUser(username, user);
    }

    @Override
    public void deleteUser(String username) {
        userRepository.deleteUser(username);
    }

}
