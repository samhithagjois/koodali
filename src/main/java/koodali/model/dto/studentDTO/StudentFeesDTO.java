package koodali.model.dto.studentDTO;

public record StudentFeesDTO(
        String studentId,
        String firstName,
        String lastName,
        int feesPaid,
        int pendingFees
) {
}
