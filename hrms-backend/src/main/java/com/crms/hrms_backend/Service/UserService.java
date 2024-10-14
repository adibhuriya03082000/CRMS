package com.crms.hrms_backend.Service;

import java.util.List;
import java.util.Map;

public interface UserService {

    public void updateUser(String username, Map<String, Object> user);

    public List<Map<String, Object>> getAllUsers();

    public void createUser(Map<String, Object> user);

    public Map<String, Object> getUserByUsername(String username);

    public void deleteUser(String username);

}
