package koodali.controller;

import koodali.model.Section;
import koodali.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import koodali.service.StudentService;

import java.util.List;


@Controller
@RequestMapping(path = "/api")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        return new ResponseEntity<>(studentService.getAll(), HttpStatus.OK);
    }

    @PutMapping("/sections")
    public ResponseEntity<Student> updateStudent(@RequestBody Student updatedStudent) {
        Student oldStudent = studentService.findByID(updatedStudent.getID());
        //Person attributes
        oldStudent.setAmountOfTextbooks(updatedStudent.getAmountOfTextbooks());
        oldStudent.setAttendancePercentage(updatedStudent.getAttendancePercentage());

        return new ResponseEntity<>(oldStudent, HttpStatus.OK);
    }

}
