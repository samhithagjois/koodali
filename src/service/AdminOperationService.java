package service;

import model.*;
import repository.AdminRepository;
import repository.SectionRepository;
import repository.StudentRepository;
import repository.TeacherRepository;
import service.exceptions.*;

public class AdminOperationService {

    private final StudentRepository studentRepo = new StudentRepository();
    private final TeacherRepository teacherRepo = new TeacherRepository();

    private final AdminRepository adminRepo = new AdminRepository();

    private final SectionRepository sectionRepo = new SectionRepository();

    public AdminOperationService() {

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
        String area = section.getName().toString().substring(0, 1);
        for (AdminPermissions permission : admin.getPermissions()) {
            if (!permission.toString().equals(area)) {
                throw new IllegalAdminActionException();
            }
        }
        return true;
    }

    /**
     * helper methods to reduce code duplication! (I sound super fancy now don't I)
     * returns the Person/Section it finds, or throws the respective Exception
     */
    private Administrator findAdmin(String adminID) throws AdminNotFoundException {
        Person admin = adminRepo
                .findByID(adminID)
                .orElseThrow(AdminNotFoundException::new);
        return (Administrator) admin;
    }

    private Teacher findTeacher(String teacherID) throws TeacherNotFoundException {
        Person teacher = teacherRepo
                .findByID(teacherID)
                .orElseThrow(TeacherNotFoundException::new);
        return (Teacher) teacher;
    }

    private Student findStudent(String studentID) throws StudentNotFoundException {
        Person student = studentRepo
                .findByID(studentID)
                .orElseThrow(StudentNotFoundException::new);
        return (Student) student;
    }

    private Section findSection(String sectionName) throws SectionNotFoundException {
        return sectionRepo
                .findSectionByName(sectionName)
                .orElseThrow(SectionNotFoundException::new);
    }


    public Student addStudentToSection(String adminID, String studentID, String sectionID)
            throws
            StudentNotFoundException,
            SectionNotFoundException,
            AdminNotFoundException,
            IllegalAdminActionException {

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
        teacher.setSection(section);
        section.getTeachers().put(teacher.getID(), teacher);
        sectionRepo.updateSection(section);
        return teacherRepo.update(teacher);


    }


    public Student reassignStudentToSection(String adminID, String studentID, String sectionID) throws IllegalAdminActionException {
        try {
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
            //change the section in the student
            student.setSection(section.getName());
            //add student to new class
            section.getStudents().put(studentID, student);
            //update section in sectionRepo
            sectionRepo.updateSection(section);
            //update student in studentRepo and return
            return studentRepo.update(student);
        } catch (StudentNotFoundException | AdminNotFoundException | SectionNotFoundException se) {
            throw new RuntimeException(se);
        }
    }


    public Student removeStudentFromSystem(String adminID, String studentID) {
        try {
            findAdmin(adminID);
        } catch (AdminNotFoundException e) {
            throw new RuntimeException(e);
        }

        return studentRepo.delete(studentID);
    }


    public Teacher removeTeacherFromSystem(String adminID, String teacherID) {

        try {
            findAdmin(adminID);
        } catch (AdminNotFoundException e) {
            throw new RuntimeException(e);
        }


        return teacherRepo.delete(teacherID);
    }


    //deleteStudentFromSection(Student student, Section section)


    //addCoTeacherToSection(Teacher teacher, Section section)
    //deleteTeacherFromSection(Teacher teacher, Section section)

}
