package com.crms.hrms_backend.Service;

import java.util.List;
import java.util.Map;

public interface ExpenseService {

    public Map<String, Object> getExpenseById(int id);

    public void createExpense(Map<String, Object> expense);

    public List<Map<String, Object>> getAllExpenses();

    public void updateExpense(int id, Map<String, Object> expense);

    public void deleteExpense(int id);
    
}
