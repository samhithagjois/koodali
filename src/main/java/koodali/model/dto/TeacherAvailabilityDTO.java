package koodali.model.dto;

import java.time.LocalDate;

public record TeacherAvailabilityDTO(String teacherID,
                                     String firstName,
                                     LocalDate week,
                                     boolean available) {
}
