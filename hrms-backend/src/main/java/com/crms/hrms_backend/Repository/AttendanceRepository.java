package com.crms.hrms_backend.Repository;

import java.util.List;
import java.util.Map;

public interface AttendanceRepository {

    public void createAttendance(Map<String, Object> attendanceData);

    public Map<String, Object> getAttendanceById(int id);

    public List<Map<String, Object>> getAllAttendances();

    public void updateAttendance(int id, Map<String, Object> attendanceData);

    public void deleteAttendance(int id);

    
}
