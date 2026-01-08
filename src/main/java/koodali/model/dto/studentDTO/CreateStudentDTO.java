package koodali.model.dto.studentDTO;

import java.time.LocalDate;

public record CreateStudentDTO(
        String firstName,
        String lastName,
        int sectionID,
        String city,
        String pinCode,
        String fullPostalAddress,
        String country,
        LocalDate dateOfBirth,
        String mothersName,
        String fathersName,
        String mothersEmailID,
        String fathersEmailID,
        String childsEmailID,
        String phoneNumber,
        String whatsappNumber,
        String pathToPhoto,
        String pathToConsentForm


) {
}
