package repository;

import model.ClassNames;
import model.Section;
import model.Student;
import org.springframework.stereotype.Component;
import service.StudentNotFoundException;

import java.util.HashMap;
import java.util.Optional;


@Component
public class StudentRepository {

    private static HashMap<String, Student> students;


    public StudentRepository(){

    }

    public boolean containsStudent(Student student){
        return students.containsValue(student);
    }

    public boolean containsSection(String studentID){
        return students.containsKey(studentID);
    }


    public static Optional<Student> findByStudentID(String studentID) {
        Optional<String> first = students.keySet().stream().filter(s -> s.equals(studentID)).findFirst();
        return first.map(s -> students.get(s));
    }

}
