package koodali.model.dto.teacherDTO;

import java.time.LocalDate;

public record CreateTeacherDTO(String firstName,
                               String lastName,
                               String city,
                               String pinCode,
                               String fullPostalAddress,
                               String country,
                               String phoneNumber,
                               String whatsappNumber,
                               int sectionID) {
}
