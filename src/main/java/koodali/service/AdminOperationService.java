package koodali.service;

import koodali.model.*;
import koodali.repository.AdminRepository;
import koodali.repository.SectionRepository;
import koodali.repository.StudentRepository;
import koodali.repository.TeacherRepository;
import koodali.service.exceptions.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminOperationService {

    /**
     * all the repositories. Here is where most of the crossover work betwwen these repositories will
     * be happening.
     */
    private final StudentRepository studentRepo = new StudentRepository();
    private final TeacherRepository teacherRepo = new TeacherRepository();

    private final AdminRepository adminRepo = new AdminRepository();

    private final SectionRepository sectionRepo = new SectionRepository();

    private final StudentService studentService;
    private final TeacherService teacherService;
    private final AdministratorService adminService;

    public AdminOperationService() {
        studentService = new StudentService(studentRepo);
        teacherService = new TeacherService(teacherRepo);
        adminService = new AdministratorService(adminRepo);
    }

    /**
     * checks the permission of the admin.
     * So the admin permission list is two  letters and corresponds with the first two letters of the section
     * we compare these two and if its not equal we throw a IllegalAdminActionException.
     * This is an internal method. I don't think this should be visible outside
     *
     * @param admin   administrator
     * @param section Section to check
     * @return true or false
     * @throws IllegalAdminActionException if for example a Munich admin tries changing Ingolstadt
     */
    public boolean checkPermissionToModify(Administrator admin, Section section) throws IllegalAdminActionException {
        String area = section.getName().toString().substring(0, 2);
        for (AdminPermissions permission : admin.getPermissions()) {
            if (!permission.toString().equals(area)) {
                throw new IllegalAdminActionException();
            }
        }
        return true;
    }

    /**
     * helper methods to reduce code duplication! (I sound super fancy now, don't I)
     * returns the Person/Section it finds, or throws the respective Exception
     */
    private Administrator findAdmin(String adminID) throws AdminNotFoundException {
        return adminRepo
                .findByID(adminID)
                .orElseThrow(AdminNotFoundException::new);
    }

    private Teacher findTeacher(String teacherID) throws TeacherNotFoundException {
        return teacherRepo
                .findByID(teacherID)
                .orElseThrow(TeacherNotFoundException::new);
    }

    private Student findStudent(String studentID) throws StudentNotFoundException {
        return studentRepo
                .findByID(studentID)
                .orElseThrow(StudentNotFoundException::new);
    }

    private Section findSection(String sectionName) throws SectionNotFoundException {
        return sectionRepo
                .findSectionByName(sectionName)
                .orElseThrow(SectionNotFoundException::new);
    }

    /**
     * creates/adds person to system
     *
     * @param person either the Student, Admin or Teacher to be added to the system
     */
    public Person addPersonToSystem(Person person) {
        if (person instanceof Student s) {
            if (studentService.contains(s)) {
                throw new DuplicatePersonException();

            }
            return studentService.save(s);

        }
        if (person instanceof Teacher t) {
            if (teacherService.contains(t)) {
                throw new DuplicatePersonException();

            }
            return teacherService.save(t);
        }
        if (person instanceof Administrator a) {
            if (adminService.contains(a)) {
                throw new DuplicatePersonException();
            }
            return adminService.save(a);
        } else throw new IllegalStateException("Unsupported person type: " + person.getClass());
    }

    /**
     * adds a List of person(s) to the System.
     */
    public List<Person> addListOfPersonsToSystem(List<Person> personList) {
        List<Person> result = new ArrayList<>();
        for (Person person : personList) {
            result.add(addPersonToSystem(person));
        }
        return result;
    }


    /**
     * adds Student to Section. first we check if we can find all we have. Then we check the permission of the admin
     * to edit this section. For example, an Erlangen admin cannot edi the Munich Intermediate 1 section.
     * Then, we set the students' section  to the section, and we add the student to the sections' student list
     * we then update the repositories.
     * So with a find - check problem - add - update structure we will go through most of this Service
     *
     * @param adminID   admins ID
     * @param studentID students ID
     * @param sectionID as String! eventhough in Student we have ClassNames, we have to find and validate the section
     *                  In our case, sectionID is simply the name of the section
     * @throws SectionNotFoundException,StudentNotFoundException,AdminNotFoundException,IllegalAdminActionException because all of this can happen
     */
    public Student addStudentToSection(String adminID, String studentID, String sectionID) {

        Student student = findStudent(studentID);
        Administrator admin = findAdmin(adminID);

        Section section = findSection(sectionID);

        if (checkPermissionToModify(admin, section)) {
            throw new IllegalAdminActionException();
        }
        student.setSection(section.getName());
        section.getStudents().put(student.getID(), student);
        sectionRepo.updateSection(section);
        return studentRepo.update(student);


    }

    /**
     * same game with the teacher, just with a different exception thrown this time!
     */
    public Teacher addTeacherToSection(String adminID, String teacherID, String sectionID) throws
            SectionNotFoundException,
            AdminNotFoundException,
            IllegalAdminActionException,
            TeacherNotFoundException {

        Administrator admin = findAdmin(adminID);

        Section section = findSection(sectionID);

        Teacher teacher = findTeacher(teacherID);

        if (checkPermissionToModify(admin, section)) {
            throw new IllegalAdminActionException();
        }
        teacher.setSection(section.getName());
        section.getTeachers().put(teacher.getID(), teacher);
        sectionRepo.updateSection(section);
        return teacherRepo.update(teacher);


    }


    /**
     * The next two methods reassign students/teachers from their old section into the new one
     * also has List methods
     */
    public Student reassignStudentToSection(String adminID, String studentID, String newSectionID) {

        Student student = findStudent(studentID);
        deleteStudentFromSection(adminID, studentID, student.getSection().toString());
        Section section = findSection(newSectionID);
        //change the section in the student
        student.setSection(section.getName());
        //add student to new class
        section.getStudents().put(studentID, student);
        //update section in sectionRepo
        sectionRepo.updateSection(section);
        //update student in studentRepo and return
        return studentRepo.update(student);

    }

    public List<Student> reassignListOfStudentsToSection(String adminID, List<String> studentIDs, String newSectionID) {
        //create new list to return
        List<Student> students = new ArrayList<>();
        //find the new section to assign the student to
        Section section = findSection(newSectionID);
        for (String id : studentIDs) {
            //find student object
            Student student = findStudent(id);
            //delete student from section
            deleteStudentFromSection(adminID, id, student.getSection().toString());
            //add section to student
            student.setSection(section.getName());
            //add student to new class
            section.getStudents().put(student.getID(), student);
            //update section in sectionRepo
            sectionRepo.updateSection(section);
            students.add(student);
        }
        return students;
    }


    public Teacher reassignTeacherToSection(String adminID, String teacherID, String sectionID) {
        Teacher teacher = deleteTeacherFromSection(adminID, teacherID, sectionID);
        ClassNames className = findSection(sectionID).getName();
        Section section = findSection(sectionID);

        teacher.setSection(className);

        section.getTeachers().put(teacherID, teacher);

        sectionRepo.updateSection(section);

        return teacherRepo.update(teacher);

    }


    /**
     * the next two methods remove the teacher/student from the system entirely.
     * This method needs special care because... well it's removing a student from the koodali.repository.
     */
    public Student removeStudentFromSystem(String adminID, String studentID) {

        Administrator admin = findAdmin(adminID);
        Student student = findStudent(studentID);
        Section section = findSection(student.getSection().toString());
        checkPermissionToModify(admin, section);

        return studentRepo.delete(studentID);
    }


    public Teacher removeTeacherFromSystem(String adminID, String teacherID) {


        Administrator admin = findAdmin(adminID);
        Teacher teacher = findTeacher(teacherID);
        Section section = findSection(teacher.getSection().toString());
        checkPermissionToModify(admin, section);


        return teacherRepo.delete(teacherID);
    }

    public void deleteStudentFromSection(String adminID, String studentID, String sectionID) {

        Student student = findStudent(studentID);
        Administrator admin = findAdmin(adminID);
        Section section = findSection(sectionID);

        if (checkPermissionToModify(admin, section)) {
            throw new IllegalAdminActionException();
        }
        //remove student from old section
        sectionRepo
                .findSectionByName(student.getSection().name())
                .ifPresent(section1 -> section1.getStudents().remove(studentID, student));
        student.setSection(null);


    }


    public Teacher deleteTeacherFromSection(String adminID, String teacherID, String sectionID) {

        Teacher teacher = findTeacher(teacherID);
        Administrator admin = findAdmin(adminID);
        Section section = findSection(sectionID);

        if (checkPermissionToModify(admin, section)) {
            throw new IllegalAdminActionException();
        }
        //remove student from old section
        sectionRepo
                .findSectionByName(teacher.getSection().toString())
                .ifPresent(section1 -> section1.getTeachers().remove(teacherID, teacher));
        teacher.setSection(null);
        return teacher;


    }


}
