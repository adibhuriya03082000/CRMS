package com.crms.hrms_backend.Service;

import java.util.List;
import java.util.Map;

public interface RoleService {

    void createRole(Map<String, Object> role);

    void updateRole(int id, Map<String, Object> role);

    void deleteRole(int id);

    List<Map<String, Object>> getAllRoles();

    Map<String, Object> getRoleById(int id);
    

}
