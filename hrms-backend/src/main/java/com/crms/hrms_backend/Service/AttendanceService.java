package com.crms.hrms_backend.Service;

import java.util.List;
import java.util.Map;

public interface AttendanceService {

    public Map<String, Object> getAttendanceById(int id);

    public List<Map<String, Object>> getAllAttendances();

    public void createAttendance(Map<String, Object> attendanceData);

    public void updateAttendance(int id, Map<String, Object> attendanceData);

    public void deleteAttendance(int id);
    

}
