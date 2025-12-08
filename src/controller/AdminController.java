package controller;

import model.Section;
import model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.*;
import service.exceptions.SectionNotFoundException;
import service.exceptions.StudentNotFoundException;

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
    //1 : manual
    //clicks on button "manage sections"
    //new view -> List of all Sections
    //select section
    // new view -> List of students and teachers in this section
    // buttons : | add student | reassign student | add teacher | reassign teacher
    // "add" view -> form view where you manually enter student/teacher data acc. to Teacher/Student in text boxes, SectionNames is a dropdown menu
    // with all the given info : create new Student/Teacher object and call StudentService to store them in StudentRepository
    //"reassign view" -> form view, where you type in student/teacher's name and the dropdown list gets smaller like when you're searching for smth
    // "from class : " and "to class : " as dropdown menus because the classes are

    @GetMapping("admin/sections")
    public ResponseEntity<List<Section>> manageSections() {
        //return sectionService.getAllSections();
        return new ResponseEntity<>(sectionService.getAllSections(), HttpStatus.OK);
    }

    //Change tghis all to ResponseEntity!
    @GetMapping("admin/section/{classId}")
    public Section selectSection(@PathVariable String classId) {
        try {
            return sectionService.getSectionByID(classId);
        } catch (SectionNotFoundException e) {
            throw new RuntimeException(e);
            //Exception handling!
        }
    }

    @GetMapping("admin/section/{classId}/students")
    public List<Student> getStudentsOfSection(@PathVariable String classId) {
        return studentService.listStudentsInSection(classId);
    }

    @GetMapping("admin/section/{classId}/students/{studentId}")
    public Student selectStudentFromSection(@PathVariable String classId, @PathVariable String studentId) {

        try {
            return getStudentsOfSection(classId)
                    .stream()
                    .filter(student -> student
                            .getID()
                            .equals(studentId))
                    .findFirst()
                    .orElseThrow(StudentNotFoundException::new);
        } catch (StudentNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("admin/students")
    public List<Student> getAllStudents() {
        return null;
    }


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
    // student service checks for duplicates, assigns to Section with s.setSection
    //StudentRepository.save


    //2 : import from excel
    // click on "import from excel file", choose excel file from local
    // call StudentExcelService to extract student info from excel file and create student objects
    // call StudentService to save said objects in StudentRepository
    //StudentRepository adds student


}
