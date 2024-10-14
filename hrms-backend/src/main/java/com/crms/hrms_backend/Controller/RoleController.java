package com.crms.hrms_backend.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crms.hrms_backend.Service.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/createrole")
    public ResponseEntity<Map<String, Object>> createRole(@RequestBody Map<String, Object> role) {
        roleService.createRole(role);
        role.put("Success", "Role created successfully");
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @PutMapping("/updaterole/{id}")
    public ResponseEntity<Map<String, Object>> updateRole(@PathVariable int id, @RequestBody Map<String, Object> role) {
        roleService.updateRole(id, role);
        role.put("Success", "Role update successfully");
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @DeleteMapping("/deleterole/{id}")
    public ResponseEntity<Map<String, Object>> deleteRole(@PathVariable int id) {
        roleService.deleteRole(id);
        Map<String, Object> role = new HashMap<>();
        role.put("Success", "Role delete successfully");
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @GetMapping("/getallroles")
    public ResponseEntity<List<Map<String, Object>>> getAllRoles() {
        List<Map<String, Object>> roles = roleService.getAllRoles();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping("/getrolebyid/{id}")
    public ResponseEntity<Map<String, Object>> getRoleById(@PathVariable int id) {
        Map<String, Object> role = roleService.getRoleById(id);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }
    
}
