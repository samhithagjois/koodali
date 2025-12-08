package controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class AdminController {
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

    @GetMapping
    public List<Section> manageSections(){
        return null;
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
