package com.crms.hrms_backend.Repository.RepositoryImpl;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.crms.hrms_backend.Repository.SalaryRepository;

@Repository
public class SalaryRepositoryImpl implements SalaryRepository {
    
    private final JdbcTemplate jdbcTemplate;

    public SalaryRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Map<String, Object> findById(int id) {
        String sql = "SELECT * FROM salary WHERE id = ?";
        return jdbcTemplate.queryForMap(sql, id);
    }

    @Override
    public List<Map<String, Object>> findAll() {
        String sql = "SELECT * FROM salary";
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public void save(Map<String, Object> salary) {
        String sql = "INSERT INTO salary (user_id, month, year, amount) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, salary.get("user_id"), salary.get("month"), salary.get("year"), salary.get("amount"));
    }

    @Override
    public void update(int id, Map<String, Object> salary) {
        String sql = "UPDATE salary SET user_id = ?, month = ?, year = ?, amount = ? WHERE id = ?";
        jdbcTemplate.update(sql, salary.get("user_id"), salary.get("month"), salary.get("year"), salary.get("amount"), id);
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM salary WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
