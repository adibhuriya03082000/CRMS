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

import com.crms.hrms_backend.Service.ExpenseService;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/getexpensebyid/{id}")
    public ResponseEntity<Map<String, Object>> getExpenseById(@PathVariable int id) {
        Map<String, Object> expense = expenseService.getExpenseById(id);
        return ResponseEntity.ok(expense);
    }

    @GetMapping("/getallexpense")
    public ResponseEntity<List<Map<String, Object>>> getAllExpenses() {
        List<Map<String, Object>> expenses = expenseService.getAllExpenses();
        return ResponseEntity.ok(expenses);
    }

    @PostMapping("/createexpense")
    public ResponseEntity<Void> createExpense(@RequestBody Map<String, Object> expense) {
        expenseService.createExpense(expense);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/updateexpense/{id}")
    public ResponseEntity<Void> updateExpense(@PathVariable int id, @RequestBody Map<String, Object> expense) {
        expenseService.updateExpense(id, expense);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deleteexpense/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable int id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }
    
}
