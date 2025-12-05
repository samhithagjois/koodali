package service;

import model.Person;
import model.Section;
import model.Student;
import org.springframework.stereotype.Service;
import repository.SectionRepository;
import repository.StudentRepository;
import service.exceptions.SectionNotFoundException;
import service.exceptions.StudentNotFoundException;

@Service
public class StudentService {

    private final StudentRepository studentRepo = new StudentRepository();

    private final SectionRepository sectionRepo = new SectionRepository();


    public StudentService() {

    }

    private Student findStudent(String studentID) throws StudentNotFoundException {
        Person student = studentRepo
                .findByID(studentID)
                .orElseThrow(StudentNotFoundException::new);
        return (Student) student;
    }

    private Section findSection(String sectionName) throws SectionNotFoundException {
        return sectionRepo
                .findSectionByName(sectionName)
                .orElseThrow(SectionNotFoundException::new);
    }

    //String personID, String firstName, String lastName, ClassNames section
    public Student createStudent(String studentId,String firstName, String lastName, String sectionID){
        try {
            Section section = findSection(sectionID);
            Student student  = new Student(studentId,firstName,lastName,section.getName());
            section.getStudents().put(student.getID(),student);
           return studentRepo.add(student);

        } catch (SectionNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //createStudent
    //createStudentsFromList
    //updateStudent

   //findStudentByID
   //listStudentsInSection



    //-> find existing student, remove from existing section, add to new section (in service!), StudentRepository.save



}
