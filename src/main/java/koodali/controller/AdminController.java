package koodali.controller;

import koodali.model.Section;
import koodali.model.Student;
import koodali.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import koodali.service.*;
import koodali.service.exceptions.StudentNotFoundException;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class AdminController {

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

    @GetMapping("admin/sections")
    public ResponseEntity<List<Section>> manageSections() {
        return new ResponseEntity<>(sectionService.getAllSections(), HttpStatus.OK);
    }

    @GetMapping("admin/sections/{classId}")
    public ResponseEntity<Section> selectSection(@PathVariable String classId) {

        return new ResponseEntity<>(sectionService.getSectionByID(classId), HttpStatus.OK);

    }

    @GetMapping("admin/sections/{classId}/students")
    public ResponseEntity<List<Student>> getStudentsOfSection(@PathVariable String classId) {
        return new ResponseEntity<>(studentService.listStudentsInSection(classId), HttpStatus.OK);
    }

    @GetMapping("admin/sections/{classId}/teachers")
    public List<Teacher> getTeachersOfSection(@PathVariable String classId) {
            return sectionService.getSectionByID(classId).getTeachers().values().stream().toList();

    }

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

    @DeleteMapping("admin/sections/{classId}")
    public ResponseEntity<Section> removeSection(@PathVariable String classId){
        Section section = sectionService.getSectionByID(classId);
        sectionService.deleteSection(section);
        return new ResponseEntity<>( sectionService.deleteSection(section),HttpStatus.OK);
    }



    //---------------------------------------------------------------
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
