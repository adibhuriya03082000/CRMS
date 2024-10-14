package com.crms.hrms_backend.Repository.RepositoryImpl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.crms.hrms_backend.Repository.RoleRepository;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void createRole(Map<String, Object> role) {
        role.put("dateOfCreation", new Date());
        String sql = "INSERT INTO roles (role_name, dateOfCreation, dateOfModification) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, role.get("role_name"), role.get("dateOfCreation"), role.get("dateOfModification"));
    }

    @Override
    public void updateRole(int id, Map<String, Object> role) {
        role.put("dateOfModification", new Date());
        String sql = "UPDATE roles SET role_name = ?, dateOfModification = ? WHERE role_id = ?";
        jdbcTemplate.update(sql,role.get("role_name"), role.get("dateOfModification"), id);
    }

    @Override
    public void deleteRole(int id) {
        String sql = "DELETE FROM roles WHERE role_id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Map<String, Object>> getAllRoles() {
        String sql = "SELECT * FROM roles";
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public Map<String, Object> getRoleById(int id) {
        String sql = "SELECT * FROM roles WHERE role_id = ?";
        return jdbcTemplate.queryForMap(sql, id);
    }
    
}
