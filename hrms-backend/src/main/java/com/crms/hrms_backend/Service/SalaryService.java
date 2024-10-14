package com.crms.hrms_backend.Service;

import java.util.List;
import java.util.Map;

public interface SalaryService {

    public Map<String, Object> getSalaryById(int id);

    public void createSalary(Map<String, Object> salary);

    public void deleteSalary(int id);

    public void updateSalary(int id, Map<String, Object> salary);

    public List<Map<String, Object>> getAllSalaries();
    
}
