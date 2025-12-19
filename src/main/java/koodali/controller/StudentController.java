package koodali.controller;

import koodali.model.Section;
import koodali.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

    @PutMapping("/students")
    public ResponseEntity<Student> updateStudent(@RequestBody Student updatedStudent) {
      //  Student oldStudent = studentService.findByID(updatedStudent.getID());
        //Person attributes


        return new ResponseEntity<>(studentService.update(updatedStudent), HttpStatus.OK);
    }

    @PostMapping("/students")
    public ResponseEntity<Student> createStudent(@RequestBody Student newStudent) {

        return new ResponseEntity<>(studentService.createStudent(newStudent), HttpStatus.CREATED);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable String id){
        return new ResponseEntity<>(studentService.delete(id),HttpStatus.OK);
    }
}
