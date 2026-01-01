package koodali.controller;

import koodali.model.Teacher;
import koodali.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api")
@CrossOrigin(origins = "http://localhost:4200")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/teachers")
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        return new ResponseEntity<>(teacherService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/teachers/{id}")
    public ResponseEntity<Teacher> getTeacher(@PathVariable String id) {
        return new ResponseEntity<>(teacherService.findByID(id), HttpStatus.OK);
    }

    @PutMapping("/teachers")
    public ResponseEntity<Teacher> updateTeacher(@RequestBody Teacher updatedTeacher) {
        return new ResponseEntity<>(teacherService.update(updatedTeacher), HttpStatus.OK);
    }


    @PostMapping("/teachers")
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher newTeacher) {
        return new ResponseEntity<>(teacherService.createTeacher(newTeacher), HttpStatus.CREATED);
    }

    @DeleteMapping("/teachers/{id}")
    public ResponseEntity<Teacher> deleteTeacher(@PathVariable String id) {
        return new ResponseEntity<>(teacherService.delete(id), HttpStatus.NO_CONTENT);
    }

    /*TeacherController
  → HomeworkExcelImportService
      → HomeworkResult list

  → LeaderboardService
      → update leaderboard koodali.repository
*/
}
