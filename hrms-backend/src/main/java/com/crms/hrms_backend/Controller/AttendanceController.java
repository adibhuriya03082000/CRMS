package com.crms.hrms_backend.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crms.hrms_backend.Service.AttendanceService;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/createattendance")
    public ResponseEntity<String> createAttendance(@RequestBody Map<String, Object> attendanceData) {
        attendanceService.createAttendance(attendanceData);
        return ResponseEntity.ok("Attendance record created successfully.");
    }

    @GetMapping("/getattendancebyid/{id}")
    public ResponseEntity<Map<String, Object>> getAttendance(@PathVariable int id) {
        Map<String, Object> attendance = attendanceService.getAttendanceById(id);
        return ResponseEntity.ok(attendance);
    }

    @GetMapping("/getattendance")
    public ResponseEntity<List<Map<String, Object>>> getAllAttendances() {
        List<Map<String, Object>> attendances = attendanceService.getAllAttendances();
        return ResponseEntity.ok(attendances);
    }

    @PutMapping("/updateattendance/{id}")
    public ResponseEntity<String> updateAttendance(@PathVariable int id, @RequestBody Map<String, Object> attendanceData) {
        attendanceService.updateAttendance(id, attendanceData);
        return ResponseEntity.ok("Attendance record updated successfully.");
    }

    @DeleteMapping("/deleteattendance/{id}")
    public ResponseEntity<String> deleteAttendance(@PathVariable int id) {
        attendanceService.deleteAttendance(id);
        return ResponseEntity.ok("Attendance record deleted successfully.");
    }
    
}
