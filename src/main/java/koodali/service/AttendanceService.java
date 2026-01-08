package koodali.service;

import koodali.model.AttendanceEntity;
import koodali.repository.AttendanceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;


    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    public double calculateAttendanceForStudent(String studentID) {
        List<LocalDate> dates = attendanceRepository
                .findAll()
                .stream()
                .map(AttendanceEntity::getWeek)
                .distinct()
                .toList();

        long count = attendanceRepository
                .findAll()
                .stream()
                .filter(a -> a.getStudentID().equals(studentID))
                .filter(AttendanceEntity::isAttended)
                .count();

        return Math.floorDiv(count, dates.size());
    }
}

