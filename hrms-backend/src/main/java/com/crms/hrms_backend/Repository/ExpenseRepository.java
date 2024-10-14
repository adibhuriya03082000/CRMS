package com.crms.hrms_backend.Repository;

import java.util.List;
import java.util.Map;

public interface ExpenseRepository {

    public List<Map<String, Object>> findAll();

    public Map<String, Object> findById(int id);

    public void update(int id, Map<String, Object> expense);

    public void save(Map<String, Object> expense);

    public void delete(int id);
    
}
