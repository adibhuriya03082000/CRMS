package com.crms.hrms_backend.Repository.RepositoryImpl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.crms.hrms_backend.Repository.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> getAllUsers() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public Map<String, Object> getUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        return jdbcTemplate.queryForMap(sql, username);
    }

    @Override
    public void createUser(Map<String, Object> user) {
        user.put("dateOfCreation", new Date());
        String sql = "INSERT INTO users (firstname, lastname, username, password, email, role_id, address, mobile, DOB, dateOfCreation, dateOfModification) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.get("firstname"), user.get("lastname"), user.get("username"), user.get("email"), user.get("password"), user.get("role_id"), user.get("address"), user.get("mobile"), user.get("DOB"), user.get("dateOfCreation"), user.get("dateOfModification"));
    }

    @Override
    public void updateUser(String username, Map<String, Object> user) {
        user.put("dateOfModification", new Date());
        String sql = "UPDATE users SET firstname = ?, lastname = ?, username = ?, password = ?, email = ?, role_id = ?, address = ?, mobile = ?,  DOB = ?, dateOfModification = ? WHERE id = ?";
        jdbcTemplate.update(sql, user.get("firstname"), user.get("lastname"), user.get("username"), user.get("password"), user.get("email"), user.get("role_id"), user.get("address"), user.get("mobile"), user.get("DOB"), user.get("dateOfModification"), username);
    }

    @Override
    public void deleteUser(String username) {
        String sql = "DELETE FROM users WHERE username = ?";
        jdbcTemplate.update(sql, username);
    }

    @Override
    public Map<String, Object> findByUsername(String username) throws UsernameNotFoundException {

        try {
            Map<String, Object> loginUser = jdbcTemplate
                    .queryForMap("SELECT * FROM `users` up JOIN roles r ON up.role_id=r.role_id WHERE up.`username`=?",
                            new Object[]{username});
            return loginUser;
        } catch (EmptyResultDataAccessException e) {
            throw new UsernameNotFoundException("User not found with username: " + username, e);

        }

    }

}
