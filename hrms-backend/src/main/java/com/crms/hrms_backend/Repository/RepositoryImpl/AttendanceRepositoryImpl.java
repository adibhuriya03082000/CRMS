package com.crms.hrms_backend.Repository.RepositoryImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.crms.hrms_backend.Repository.AttendanceRepository;

@Repository
public class AttendanceRepositoryImpl implements AttendanceRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void createAttendance(Map<String, Object> attendanceData) {
        String sql = "INSERT INTO attendance (user_id, date, status, check_in_time, check_out_time, total_hours_worked, late_status, overtime_hours, remarks) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                attendanceData.get("user_id"),
                attendanceData.get("date"),
                attendanceData.get("status"),
                attendanceData.get("check_in_time"),
                attendanceData.get("check_out_time"),
                attendanceData.get("total_hours_worked"),
                attendanceData.get("late_status"),
                attendanceData.get("overtime_hours"),
                attendanceData.get("remarks")
        );
    }

    @Override
    public Map<String, Object> getAttendanceById(int id) {
        String sql = "SELECT * FROM attendance WHERE id = ?";
        return jdbcTemplate.queryForMap(sql, id);
    }

    @Override
    public List<Map<String, Object>> getAllAttendances() {
        String sql = "SELECT * FROM attendance";
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public void updateAttendance(int id, Map<String, Object> attendanceData) {
        String sql = "UPDATE attendance SET user_id = ?, date = ?, status = ?, check_in_time = ?, check_out_time = ?, " +
                "total_hours_worked = ?, late_status = ?, overtime_hours = ?, remarks = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                attendanceData.get("user_id"),
                attendanceData.get("date"),
                attendanceData.get("status"),
                attendanceData.get("check_in_time"),
                attendanceData.get("check_out_time"),
                attendanceData.get("total_hours_worked"),
                attendanceData.get("late_status"),
                attendanceData.get("overtime_hours"),
                attendanceData.get("remarks"),
                id
        );
    }

    @Override
    public void deleteAttendance(int id) {
        String sql = "DELETE FROM attendance WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
    
}
