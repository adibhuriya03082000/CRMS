package com.crms.hrms_backend.Repository;

import java.util.List;
import java.util.Map;

public interface UserRepository {

    public Map<String, Object> getUserByUsername(String username);

    public List<Map<String, Object>> getAllUsers();

    public void createUser(Map<String, Object> user);

    public void deleteUser(String username);

    public void updateUser(String username, Map<String, Object> user);

    public Map<String, Object> findByUsername(String username);

}
