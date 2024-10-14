package com.crms.hrms_backend.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crms.hrms_backend.Service.SalaryService;

@RestController
@RequestMapping("/salary")
public class SalaryController {

    private final SalaryService salaryService;

    public SalaryController(SalaryService salaryService) {
        this.salaryService = salaryService;
    }

    @GetMapping("/getsalarybyid/{id}")
    public ResponseEntity<Map<String, Object>> getSalaryById(@PathVariable int id) {
        Map<String, Object> salary = salaryService.getSalaryById(id);
        return ResponseEntity.ok(salary);
    }

    @GetMapping("/getallsalary")
    public ResponseEntity<List<Map<String, Object>>> getAllSalaries() {
        List<Map<String, Object>> salaries = salaryService.getAllSalaries();
        return ResponseEntity.ok(salaries);
    }

    @PostMapping("/createsalary")
    public ResponseEntity<Void> createSalary(@RequestBody Map<String, Object> salary) {
        salaryService.createSalary(salary);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/updatesalary/{id}")
    public ResponseEntity<Void> updateSalary(@PathVariable int id, @RequestBody Map<String, Object> salary) {
        salaryService.updateSalary(id, salary);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deletesalary/{id}")
    public ResponseEntity<Void> deleteSalary(@PathVariable int id) {
        salaryService.deleteSalary(id);
        return ResponseEntity.noContent().build();
    }
    
}
