package com.crms.hrms_backend.Service.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.crms.hrms_backend.Repository.SalaryRepository;
import com.crms.hrms_backend.Service.SalaryService;

@Service
public class SalaryServiceImpl implements SalaryService {

    private final SalaryRepository salaryRepository;

    public SalaryServiceImpl(SalaryRepository salaryRepository) {
        this.salaryRepository = salaryRepository;
    }

    @Override
    public Map<String, Object> getSalaryById(int id) {
        return salaryRepository.findById(id);
    }

    @Override
    public List<Map<String, Object>> getAllSalaries() {
        return salaryRepository.findAll();
    }

    @Override
    public void createSalary(Map<String, Object> salary) {
        salaryRepository.save(salary);
    }

    @Override
    public void updateSalary(int id, Map<String, Object> salary) {
        salaryRepository.update(id, salary);
    }

    @Override
    public void deleteSalary(int id) {
        salaryRepository.delete(id);
    }
    
}
