package com.crms.hrms_backend.Service.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crms.hrms_backend.Repository.AttendanceRepository;
import com.crms.hrms_backend.Service.AttendanceService;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Override
    public void createAttendance(Map<String, Object> attendanceData) {
        attendanceRepository.createAttendance(attendanceData);
    }

    @Override
    public Map<String, Object> getAttendanceById(int id) {
        return attendanceRepository.getAttendanceById(id);
    }

    @Override
    public List<Map<String, Object>> getAllAttendances() {
        return attendanceRepository.getAllAttendances();
    }

    @Override
    public void updateAttendance(int id, Map<String, Object> attendanceData) {
        attendanceRepository.updateAttendance(id, attendanceData);
    }

    @Override
    public void deleteAttendance(int id) {
        attendanceRepository.deleteAttendance(id);
    }
    
}
