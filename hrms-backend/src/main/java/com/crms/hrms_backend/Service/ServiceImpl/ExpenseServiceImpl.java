package com.crms.hrms_backend.Service.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.crms.hrms_backend.Repository.ExpenseRepository;
import com.crms.hrms_backend.Service.ExpenseService;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepository expenseRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public Map<String, Object> getExpenseById(int id) {
        return expenseRepository.findById(id);
    }

    @Override
    public List<Map<String, Object>> getAllExpenses() {
        return expenseRepository.findAll();
    }

    @Override
    public void createExpense(Map<String, Object> expense) {
        expenseRepository.save(expense);
    }

    @Override
    public void updateExpense(int id, Map<String, Object> expense) {
        expenseRepository.update(id, expense);
    }

    @Override
    public void deleteExpense(int id) {
        expenseRepository.delete(id);
    }
    
}
