package repository;

import model.ClassNames;
import model.Section;
import model.Student;
import org.springframework.stereotype.Component;

import java.util.HashMap;


@Component
public class StudentRepository {

    private static HashMap<String, Student> students;


    public StudentRepository(){

    }


    public static Student findByStudentID(String studentID) throws StudentNotFoundException {
        if(students.containsKey(studentID)){
            return students.get(studentID);
        }else {
            throw new StudentNotFoundException();
        }

    }

}
