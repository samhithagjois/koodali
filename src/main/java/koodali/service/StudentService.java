package koodali.service;

import koodali.model.ClassNames;
import koodali.model.Section;
import koodali.model.Student;
import org.springframework.stereotype.Service;
import koodali.repository.StudentRepository;
import koodali.service.exceptions.SectionNotFoundException;
import koodali.service.exceptions.StudentNotFoundException;

import java.util.Date;
import java.util.List;

@Service
public class StudentService extends PersonService<Student>{

    private final StudentRepository studentRepo;

    private final SectionService sectionService;


    public StudentService(StudentRepository studentRepo) {
       super(studentRepo);
        this.studentRepo = studentRepo;
        this.sectionService = new SectionService();
    }

    public Student findByID(String studentID) {
        return studentRepo
                .findByID(studentID)
                .orElseThrow(StudentNotFoundException::new);
    }

    public Student findStudentbyName(String name){
        return studentRepo
                .findByName(name)
                .orElseThrow(StudentNotFoundException::new);
    }


    public Student createStudent(String studentId, String firstName, String lastName, String sectionID) {

            Section section = sectionService.getSectionByID(sectionID);
            Student student = new Student(studentId, firstName, lastName, section.getName());
            section.getStudents().put(student.getID(), student);
            return studentRepo.add(student);

    }

    public Student createStudent(String studentId,
                                 String firstName,
                                 String lastName,
                                 ClassNames sectionID,
                                 String city, String pinCode, String country,
                                 String fullPostalAdress, Date dateOfRegistration,
                                 Date dateOfClassStart, boolean activeStatus,
                                 int amountOfTextbooks, int feesPaid, int pendingFees, int homeworkLeaderBoardScore,
                                 Date dateOfBirth,
                                 Date dateOfFirstClass, String mothersName, String fathersName,
                                 String fathersEmailID, String mothersEmailID, double attendancePercentage,
                                 float phoneNumber, float whatsappNumber) {

        Section section = sectionService.getSectionByID(sectionID.toString());
        Student student = new Student(
            firstName,lastName,studentId,city,pinCode,country,fullPostalAdress,
                dateOfRegistration,dateOfClassStart,activeStatus, sectionID,
                amountOfTextbooks,feesPaid, pendingFees, homeworkLeaderBoardScore,
                dateOfBirth,dateOfFirstClass,mothersName,fathersName,fathersEmailID,
                mothersEmailID,attendancePercentage,phoneNumber,whatsappNumber
        );
        section.getStudents().put(student.getID(), student);
        return studentRepo.add(student);

    }

    public boolean createStudentsFromList(List<Student> students) {
        for (Student student : students) {
            studentRepo.add(student);
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
        return true;
    }

    public Student addHomeworkPoints(String studentID,String week, int points){
        Student student = findByID(studentID);
        student.getHomeworkPointsPerWeek().put(week,points);
        student.setHomeworkLeaderBoardScore(student.getHomeworkLeaderBoardScore() + points);
        return update(student);

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
