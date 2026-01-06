package koodali.model.dto.studentDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record StudentOverviewDTO(
        String personID,
        String firstName,
        String lastName,
        String city,
        String pinCode,
        String country,
        String fullPostalAddress,
        LocalDateTime dateOfRegistration,
        LocalDateTime dateOfClassStart,
        boolean activeStatus,
        String sectionId,
        int amountOfTextbooks,
        int feesPaid,
        int pendingFees,
        int homeworkLeaderBoardScore,
        LocalDate dateOfBirth,
        LocalDate dateOfFirstClass,
        String pathToPhoto,
        String pathToConsentForm,
        String mothersName,
        String fathersName,
        String fathersEmailID,
        String mothersEmailID,
        String childEmailID,
        String phoneNumber,
        String whatsappNumber
) {
}
