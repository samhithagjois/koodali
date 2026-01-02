package koodali.controller;

import koodali.model.Student;
import koodali.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping(path = "/api")
@CrossOrigin(origins = "http://localhost:4200")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /*
    * ----------------------------------GET MAPPINGS----------------------------------
    */
    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        return new ResponseEntity<>(studentService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable String id) {
        return new ResponseEntity<>(studentService.findByID(id), HttpStatus.OK);
    }
    /*
     * ----------------------------------PUT MAPPINGS----------------------------------
     */
    @PutMapping("/students")
    public ResponseEntity<Student> updateStudent(@RequestBody Student updatedStudent) {
         return new ResponseEntity<>(studentService.update(updatedStudent), HttpStatus.OK);
    }

    /*
     * ----------------------------------POST MAPPINGS----------------------------------
     */
    @PostMapping("/students")
    public ResponseEntity<Student> createStudent(@RequestBody Student newStudent) {
        return new ResponseEntity<>(studentService.createStudent(newStudent), HttpStatus.CREATED);
    }
    /*
     * ----------------------------------DELETE MAPPINGS----------------------------------
     */
    @DeleteMapping("/students/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable String id) {
        return new ResponseEntity<>(studentService.delete(id), HttpStatus.NO_CONTENT);
    }
}
