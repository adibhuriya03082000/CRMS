package com.crms.hrms_backend.Repository.RepositoryImpl;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.crms.hrms_backend.Repository.ExpenseRepository;

@Repository
public class ExpenseRepositoryImpl implements ExpenseRepository {

    private final JdbcTemplate jdbcTemplate;

    public ExpenseRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Map<String, Object> findById(int id) {
        String sql = "SELECT * FROM expense WHERE id = ?";
        return jdbcTemplate.queryForMap(sql, id);
    }

    @Override
    public List<Map<String, Object>> findAll() {
        String sql = "SELECT * FROM expense";
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public void save(Map<String, Object> expense) {
        String sql = "INSERT INTO expense (user_id, expense_type, date, amount, description) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, expense.get("user_id"), expense.get("expense_type"), expense.get("date"), expense.get("amount"), expense.get("description"));
    }

    @Override
    public void update(int id, Map<String, Object> expense) {
        String sql = "UPDATE expense SET user_id = ?, expense_type = ?, date = ?, amount = ?, description = ? WHERE id = ?";
        jdbcTemplate.update(sql, expense.get("user_id"), expense.get("expense_type"), expense.get("date"), expense.get("amount"), expense.get("description"), id);
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM expense WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
    
}
