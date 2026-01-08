package koodali.service;

import koodali.model.Section;
import koodali.model.Student;
import koodali.model.dto.studentDTO.CreateStudentDTO;
import koodali.model.dto.studentDTO.SectionStudentOverviewDTO;
import koodali.model.dto.studentDTO.StudentFeesDTO;
import koodali.model.dto.studentDTO.StudentOverviewDTO;
import koodali.repository.StudentRepository;
import koodali.service.exceptions.SectionNotFoundException;
import koodali.service.exceptions.StudentNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class StudentService extends PersonService<Student> {

    private final StudentRepository studentRepo;

    private final SectionService sectionService;


    public StudentService(StudentRepository studentRepo, SectionService sectionService) {
        super(studentRepo);
        this.studentRepo = studentRepo;

        this.sectionService = sectionService;
    }

    private CreateStudentDTO StudentToCreateStudentDTO(Student student) {

        return new CreateStudentDTO(
                student.getFirstName(),
                student.getLastName(),
                0,
                student.getCity(),
                student.getPinCode(),
                student.getFullPostalAdress(),
                student.getCountry(),
                student.getDateOfBirth(),
                student.getMothersName(),
                student.getFathersName(),
                student.getMothersEmailID(),
                student.getFathersEmailID(),
                student.getChildEmailID(),
                student.getPhoneNumber(),
                student.getWhatsappNumber(),
                student.getPathToPhoto(),
                student.getPathToPhoto()
        );
    }

    private Student CreateStudentDTOToStudent(CreateStudentDTO dto) {
        return new Student(dto.firstName(),
                dto.lastName(),
                "NKS_" + (UUID.randomUUID().toString().substring(0, 2)),
                dto.city(),
                dto.pinCode(),
                dto.country(), dto.fullPostalAddress(), LocalDateTime.now(), LocalDateTime.now(), false, sectionService.getSectionByID(dto.sectionID()).getName(),
                0, 0, 0, 0, dto.dateOfBirth(), LocalDate.now(), dto.pathToPhoto(), dto.pathToConsentForm(), dto.mothersName(), dto.fathersName(), dto.fathersEmailID(),
                dto.mothersEmailID(), dto.childsEmailID(), 0.0, dto.phoneNumber(), dto.whatsappNumber());
    }

    private StudentOverviewDTO StudentToStudentOverviewDTO(Student student) {

        return new StudentOverviewDTO(
                student.isActiveStatus(),
                student.getID(),
                student.getFirstName(),
                student.getLastName(),
                student.getDateOfBirth(),
                student.getCity(),
                student.getPinCode(),
                student.getCountry(),
                student.getFullPostalAdress(),
                student.getDateOfRegistration(),
                student.getDateOfClassStart(),
                student.getSection(),
                student.getAmountOfTextbooks(),
                student.getFeesPaid(),
                student.getPendingFees(),
                student.getHomeworkLeaderBoardScore(),
                student.getDateOfFirstClass(),
                student.getPathToPhoto(),
                student.getPathToConsentForm(),
                student.getMothersName(),
                student.getFathersName(),
                student.getMothersEmailID(),
                student.getFathersEmailID(),
                student.getChildEmailID(),
                student.getPhoneNumber(),
                student.getWhatsappNumber()
        );
    }

    private SectionStudentOverviewDTO StudentToSectionStudentOverviewDTO(Student student) {

        return new SectionStudentOverviewDTO(
                student.getID(),
                student.getFirstName(),
                student.getLastName(),
                student.getHomeworkLeaderBoardScore(),
                student.getAttendance()
        );
    }

    private StudentFeesDTO StudentToStudentFeeDTO(Student student) {

        return new StudentFeesDTO(
                student.getID(),
                student.getFirstName(),
                student.getLastName(),
                student.getFeesPaid(),
                student.getPendingFees()
        );
    }

    public Student findByID(String studentID) {
        return studentRepo
                .findById(studentID)
                .orElseThrow(StudentNotFoundException::new);
    }

    public Student findStudentbyName(String firstName, String lastName) {
        return studentRepo
                .findByFirstNameIgnoreCaseOrLastNameIgnoreCase(firstName, lastName)
                .orElseThrow(StudentNotFoundException::new);
    }


    public CreateStudentDTO createStudent(CreateStudentDTO student) {


        return StudentToCreateStudentDTO(studentRepo.save(CreateStudentDTOToStudent(student)));
    }

    public CreateStudentDTO updateStudent(CreateStudentDTO student) {
        Student s = findStudentbyName(student.firstName(), student.lastName());
        //TODO:alllllllllllll thissssssssss
        return student;


    }


    public Student createStudent(String studentId,
                                 String firstName,
                                 String lastName,
                                 String sectionID,
                                 String city, String pinCode, String country,
                                 String fullPostalAddress, LocalDateTime dateOfRegistration,
                                 LocalDateTime dateOfClassStart, boolean activeStatus,
                                 int amountOfTextbooks, int feesPaid, int pendingFees, int homeworkLeaderBoardScore,
                                 LocalDate dateOfBirth,
                                 LocalDate dateOfFirstClass, String pathToPhoto,
                                 String pathToConsentForm, String mothersName, String fathersName,
                                 String fathersEmailID, String mothersEmailID, String childEmailAddress, double attendancePercentage,
                                 String phoneNumber, String whatsappNumber) {

        Section section = sectionService.getSectionByName(sectionID);
        Student student = new Student(
                firstName, lastName, studentId, city, pinCode, country, fullPostalAddress,
                dateOfRegistration, dateOfClassStart, activeStatus, sectionID,
                amountOfTextbooks, feesPaid, pendingFees, homeworkLeaderBoardScore,
                dateOfBirth, dateOfFirstClass, pathToPhoto, pathToConsentForm, mothersName, fathersName, fathersEmailID,
                mothersEmailID, childEmailAddress, attendancePercentage, phoneNumber, whatsappNumber
        );
        section.getStudents().put(student.getID(), student);
        return studentRepo.save(student);

    }

    public void createStudentsFromList(List<Student> students) {
        for (Student student : students) {
            studentRepo.save(student);
            sectionService
                    .getAllSections()
                    .stream()
                    .filter(
                            section -> section
                                    .getName()
                                    .equals(student.getSection()))
                    .findFirst()
                    .ifPresent(section -> section
                            .getStudents()
                            .put(student.getID(), student));
        }
    }

    public Student addHomeworkPoints(String studentID, LocalDate week, int points) {
        Student student = findByID(studentID);
        student.setHomeworkLeaderBoardScore(student.getHomeworkLeaderBoardScore() + points);
        return update(student);

    }

    public int getTotalPoints(String studentID) {
        Student student = findByID(studentID);
        return student.getHomeworkLeaderBoardScore();
    }


    public List<Student> listStudentsInSection(String sectionID) {
        try {
            Section section = sectionService.getSectionByName(sectionID);
            return section.getStudents().values().stream().toList();
        } catch (SectionNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}
