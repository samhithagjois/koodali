package koodali.controller;

import koodali.model.Section;
import koodali.model.Student;
import koodali.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
@CrossOrigin(origins = "http://localhost:4200")
public class SectionController {

    private final SectionService sectionService;

    @Autowired
    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    /**
     *--------------------- GET Mappings------------------------------------
     * */

    /**
     * gets all sections and returns them as a List
     * */
    @GetMapping("/sections")
    public ResponseEntity<List<Section>> getAllSections() {
        return new ResponseEntity<>(sectionService.getAllSections(), HttpStatus.OK);
    }

    /**
     * gets a specified Section. Can throw SectionNotFoundException
     * */
    @GetMapping("/sections/{id}")
    public ResponseEntity<Section> getSection(@PathVariable String id){
        return new ResponseEntity<>(sectionService.getSectionByID(id), HttpStatus.OK);
    }

    @GetMapping("/sections/{classId}/students")
    public ResponseEntity<List<String>> showInfoStudentsOfSection(@PathVariable String classId) {
        List<String> names = sectionService.getSectionByID(classId)
                .getStudents()
                .values()
                .stream()
                .map(
                        student -> student.getFirstName() + " " + student.getLastName()
                                + " from " + student.getCity() + " , " + student.getCountry() + " , Section :"
                                + classId

                ).toList();

        return new ResponseEntity<>(names, HttpStatus.OK);
    }

    @GetMapping("/sections/{classId}/teachers")
    public ResponseEntity<List<String>> showInfoTeachersOfSection(@PathVariable String classId) {
        List<String> names = sectionService.getSectionByID(classId)
                .getTeachers()
                .values()
                .stream()
                .map(
                        teacher -> teacher.getFirstName() + " " + teacher.getLastName()
                                + " , teaching class " + classId + " since "+teacher.getDateOfClassStart().format(DateTimeFormatter.ISO_DATE)

                ).toList();

        return new ResponseEntity<>(names, HttpStatus.OK);
    }

    /**
     * -------------------- POST Mappings -------------------------------
     * */
    @PostMapping("/sections")
    public ResponseEntity<Section> createSection(@RequestBody Section newSection) {
        return new ResponseEntity<>(sectionService.createSection(newSection), HttpStatus.CREATED);
    }

    /**
     * -------------------- PUT Mappings -------------------------------
     * */
    @PutMapping("/sections")
    public ResponseEntity<Section> updateSection(@RequestBody Section updatedSection) {
        return new ResponseEntity<>(sectionService.updateSection(updatedSection), HttpStatus.OK);
    }

    /**
     * -------------------- DELETE Mappings -------------------------------
     * */

}
