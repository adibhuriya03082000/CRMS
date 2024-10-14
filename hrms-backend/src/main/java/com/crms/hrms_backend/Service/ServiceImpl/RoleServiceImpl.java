package com.crms.hrms_backend.Service.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crms.hrms_backend.Repository.RoleRepository;
import com.crms.hrms_backend.Service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void createRole(Map<String, Object> role) {
        roleRepository.createRole(role);
    }

    @Override
    public void updateRole(int id, Map<String, Object> role) {
        roleRepository.updateRole(id, role);
    }

    @Override
    public void deleteRole(int id) {
        roleRepository.deleteRole(id);
    }

    @Override
    public List<Map<String, Object>> getAllRoles() {
        return roleRepository.getAllRoles();
    }

    @Override
    public Map<String, Object> getRoleById(int id) {
        return roleRepository.getRoleById(id);
    }
    
}
