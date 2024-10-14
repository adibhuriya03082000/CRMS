package com.crms.hrms_backend.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crms.hrms_backend.Repository.UserRepository;
import com.crms.hrms_backend.Service.UserService;
import com.crms.hrms_backend.Service.ServiceImpl.UserDetailServiceImpl;
import com.crms.hrms_backend.Utils.JwtUtills;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository dbLoginReadRepository;
    @Autowired
    private UserDetailServiceImpl userDetailServiceImpl;
    @Autowired
    private JwtUtills jwtUtills;

    @GetMapping("/getallusers")
    public ResponseEntity<List<Map<String, Object>>> getAllUsers() {
        List<Map<String, Object>> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/getuserbyid/{username}")
    public ResponseEntity<Map<String, Object>> getUserById(@PathVariable String username) {
        Map<String, Object> users = userService.getUserByUsername(username);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/createuser")
    public ResponseEntity<Map<String, Object>> createUser(@RequestBody Map<String, Object> user) {
        userService.createUser(user);
        user.put("Success", "User created successfully");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/updateuser/{username}")
    public ResponseEntity<Map<String, Object>> updateUser(@PathVariable String username, @RequestBody Map<String, Object> user) {
        userService.updateUser(username, user);
        user.put("Success", "User update successfully");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/deleteuser/{username}")
    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        Map<String, Object> user = new HashMap<>();
        user.put("Success", "User delete successfully");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> request) {

        UserDetails userDetails = this.userDetailServiceImpl.loadUserByUsername(request.get("username").toString());
        //Map<String, Object> user = dbLoginReadRepository.findByUsername(request.get("username").toString());

        String token = jwtUtills.getToken(userDetails);
        Map<String, Object> _map = new HashMap<>();
        _map.put("token", token);
        return ResponseEntity.ok(_map);
    }

}
