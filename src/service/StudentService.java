package service;

import model.Person;
import model.Section;
import model.Student;
import org.springframework.stereotype.Service;
import repository.SectionRepository;
import repository.StudentRepository;
import service.exceptions.SectionNotFoundException;
import service.exceptions.StudentNotFoundException;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepo = new StudentRepository();

    private final SectionRepository sectionRepo = new SectionRepository();

    private final StudentExcelService studentExcelService = new StudentExcelService();

    public StudentService() {

    }

    public Student findStudentbyID(String studentID) throws StudentNotFoundException {
        Person student = studentRepo
                .findByID(studentID)
                .orElseThrow(StudentNotFoundException::new);
        return (Student) student;
    }

    public Student findStudentbyName(String name) throws StudentNotFoundException {
        Person student = studentRepo
                .findByName(name)
                .orElseThrow(StudentNotFoundException::new);
        return (Student) student;
    }

    private Section findSection(String sectionName) throws SectionNotFoundException {
        return sectionRepo
                .findSectionByName(sectionName)
                .orElseThrow(SectionNotFoundException::new);
    }

    public Student createStudent(String studentId, String firstName, String lastName, String sectionID) {
        try {

            Section section = findSection(sectionID);
            Student student = new Student(studentId, firstName, lastName, section.getName());
            section.getStudents().put(student.getID(), student);
            return studentRepo.add(student);

        } catch (SectionNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean createStudentsFromList(List<Student> students) {
        for (Student student : students) {
            studentRepo.add(student);
            sectionRepo
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


    public Student updateStudent(Student student) {
        return studentRepo.update(student);
    }


    public List<Student> listStudentsInSection(String sectionID) {
        try {
            Section section = findSection(sectionID);
            return section.getStudents().values().stream().toList();
        } catch (SectionNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Student> getAllStudents() {
        return studentRepo.getAll();
    }


}
