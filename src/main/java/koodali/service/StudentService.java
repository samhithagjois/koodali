package koodali.service;

import koodali.model.ClassNames;
import koodali.model.Section;
import koodali.model.Student;
import koodali.repository.StudentRepository;
import koodali.service.exceptions.SectionNotFoundException;
import koodali.service.exceptions.StudentNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class StudentService extends PersonService<Student> {

    private final StudentRepository studentRepo;

    private final SectionService sectionService;


    public StudentService(StudentRepository studentRepo, SectionService sectionService) {
        super(studentRepo);
        this.studentRepo = studentRepo;

        this.sectionService = sectionService;
    }

    public Student findByID(String studentID) {
        return studentRepo
                .findById(studentID)
                .orElseThrow(StudentNotFoundException::new);
    }

    public Student findStudentbyName(String firstName,String lastName) {
        return studentRepo
                .findByFirstNameIgnoreCaseOrLastNameIgnoreCase(firstName,lastName)
                .orElseThrow(StudentNotFoundException::new);
    }

    public double calculateAttendancePercentage(String studentID, String classID) {
        Student student = findByID(studentID);
        Section section = sectionService.getSectionByID(classID);
        List<LocalDate> dates = section
                .getClassSchedule()
                .keySet()
                .stream()
                .toList();
        List<LocalDate> studentAttended = student
                .getAttendance()
                .entrySet()
                .stream()
                .filter(Map.Entry::getValue)
                .map(Map.Entry::getKey)
                .toList();

        //TODO : test!
        return (double) studentAttended.size() / dates.size();

    }

    public Student createStudent(Student student) {
        Section section = sectionService.getSectionByID(student.getSection().toString());
        section.getStudents().put(student.getID(), student);
        return studentRepo.save(student);
    }

    public Student createStudent(String studentId, String firstName, String lastName, String sectionID) {

        Section section = sectionService.getSectionByID(sectionID);
        Student student = new Student(studentId, firstName, lastName, section.getName());
        section.getStudents().put(student.getID(), student);
        return studentRepo.save(student);

    }

    public Student createStudent(String studentId,
                                 String firstName,
                                 String lastName,
                                 ClassNames sectionID,
                                 String city, String pinCode, String country,
                                 String fullPostalAdress, LocalDateTime dateOfRegistration,
                                 LocalDateTime dateOfClassStart, boolean activeStatus,
                                 int amountOfTextbooks, int feesPaid, int pendingFees, int homeworkLeaderBoardScore,
                                 LocalDate dateOfBirth,
                                 LocalDate dateOfFirstClass, String mothersName, String fathersName,
                                 String fathersEmailID, String mothersEmailID, Map<LocalDate, Boolean> attendancePercentage,
                                 String phoneNumber, String whatsappNumber) {

        Section section = sectionService.getSectionByID(sectionID.toString());
        Student student = new Student(
                firstName, lastName, studentId, city, pinCode, country, fullPostalAdress,
                dateOfRegistration, dateOfClassStart, activeStatus, sectionID,
                amountOfTextbooks, feesPaid, pendingFees, homeworkLeaderBoardScore,
                dateOfBirth, dateOfFirstClass, mothersName, fathersName, fathersEmailID,
                mothersEmailID, attendancePercentage, phoneNumber, whatsappNumber
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
        student.getHomeworkPointsPerWeek().put(week, points);
        student.setHomeworkLeaderBoardScore(student.getHomeworkLeaderBoardScore() + points);
        return update(student);

    }

    public int getTotalPoints(String studentID){
        Student student = findByID(studentID);
        return student.getHomeworkLeaderBoardScore();
    }


    public List<Student> listStudentsInSection(String sectionID) {
        try {
            Section section = sectionService.getSectionByID(sectionID);
            return section.getStudents().values().stream().toList();
        } catch (SectionNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}
