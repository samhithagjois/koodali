package koodali.model.dto;

import java.time.LocalDate;

public record AttendanceDTO(
        String studentId,
        String studentName,
        LocalDate date,
        boolean present) {

}
