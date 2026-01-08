package koodali.controller;

import koodali.model.Student;
import koodali.model.dto.SectionDTO;
import koodali.service.*;
import koodali.service.exceptions.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {

    /**
     * All the Service classes needed for our Admin Operations
     */
    private final AdminOperationService adminOperationService;

    private final AdministratorService adminService;
    private final StudentService studentService;
    private final SectionService sectionService;
    private final TeacherService teacherService;

    @Autowired
    public AdminController(AdminOperationService adminOperationService,
                           AdministratorService adminService,
                           StudentService studentService,
                           SectionService sectionService,
                           TeacherService teacherService) {

        this.adminOperationService = adminOperationService;
        this.adminService = adminService;
        this.studentService = studentService;
        this.sectionService = sectionService;
        this.teacherService = teacherService;
    }

    //___________________________

    /**
     * this method gets all sections and displays them in a List
     */
    @GetMapping("admin/sections")
    public ResponseEntity<List<SectionDTO>> manageSections() {
        return new ResponseEntity<>(sectionService.getAllSectionsDTO(), HttpStatus.OK);
    }

    /**
     * this method selects a section
     *
     * @param classId the name of the class
     * @return Section
     */
    @GetMapping("admin/sections/{classId}")
    public ResponseEntity<SectionDTO> selectSection(@PathVariable String classId) {

        return new ResponseEntity<>(sectionService.getSectionDTOByName(classId), HttpStatus.OK);

    }


    /**
     * this method selects a Student from the section
     *
     * @param classId   class name
     * @param studentId student ID
     */
    @GetMapping("admin/section/{classId}/students/{studentId}")
    public ResponseEntity<Student> selectStudentFromSection(@PathVariable String classId, @PathVariable String studentId) {

        Student s = studentService
                .listStudentsInSection(classId)
                .stream()
                .filter(student -> student
                        .getID()
                        .equals(studentId))
                .findFirst()
                .orElseThrow(StudentNotFoundException::new);


        return new ResponseEntity<>(s, HttpStatus.OK);

    }

    //-------------

    /**
     * remove a Section from the Section repository
     *
     * @param classId class name
     */
    @DeleteMapping("admin/sections/{classId}")
    public ResponseEntity<SectionDTO> removeSection(@PathVariable String classId) {

        return new ResponseEntity<>(sectionService.deleteSectionbyId(classId), HttpStatus.OK);
    }

//-------------

    /**
     * updates the section that exists already
     *
     * @param updatedSection Section object that is updated
     */
    @PutMapping("admin/sections")
    public ResponseEntity<SectionDTO> updateSection(@RequestBody SectionDTO updatedSection) {

        return new ResponseEntity<>(sectionService.updateSection(updatedSection), HttpStatus.OK);
    }


    /**
     * reassigns a student to the new section.
     *
     * @param adminId   ID of the admin
     * @param sectionId name of the new class
     * @param studentId ID of the student
     */
    @PutMapping("admin/sections/{sectionId}/students/{studentId}")
    ResponseEntity<Student> reassignStudent(String adminId, @PathVariable String sectionId, @PathVariable String studentId) {
        Student s = adminOperationService.reassignStudentToSection(adminId, studentId, sectionId);
        //TODO 6: Security Context!
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

    @PutMapping("admin/sections/{sectionId}/students")
    ResponseEntity<Student> addStudentToSection(@PathVariable String sectionId, String studentId, String adminId) {
        Student s = adminOperationService.addStudentToSection(adminId, studentId, sectionId);
        //TODO 6: Security Context!
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

    //---------------------------------------------------------------

    /**
     * Post mappings
     * */


    //-----------------------------------------------------------

    /**
     * getters
     */
    public AdminOperationService getAdminOperationService() {
        return adminOperationService;
    }

    public AdministratorService getAdminService() {
        return adminService;
    }

    public StudentService getStudentService() {
        return studentService;
    }

    public SectionService getSectionService() {
        return sectionService;
    }

    public TeacherService getTeacherService() {
        return teacherService;
    }


    // logic :
    // AdminController receives form
    // call studentService.createStudent(... all above)
    // student koodali.service checks for duplicates, assigns to Section with s.setSection
    //StudentRepository.save


    //2 : import from excel
    // click on "import from excel file", choose excel file from local
    // call StudentExcelService to extract student info from excel file and create student objects
    // call StudentService to save said objects in StudentRepository
    //StudentRepository adds student


}
