package com.crms.hrms_backend.Repository;

import java.util.List;
import java.util.Map;

public interface SalaryRepository {
    
    Map<String, Object> findById(int id);
    
    List<Map<String, Object>> findAll();
    
    void save(Map<String, Object> salary);
    
    void update(int id, Map<String, Object> salary);
    
    void delete(int id);
}
