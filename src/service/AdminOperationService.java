package service;

import model.*;
import repository.AdminRepository;
import repository.SectionRepository;
import repository.StudentRepository;
import repository.TeacherRepository;
import service.exceptions.*;

public class AdminOperationService {

    /**
     * all the repositories. Here is where most of the crossover work betwwen these repositories will
     * be happening.
     * */
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
     * helper methods to reduce code duplication! (I sound super fancy now, don't I)
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


    /**
     * adds Student to Section. first we check if we can find all we have. Then we check the permission of the admin
     * to edit this section. For example, an Erlangen admin cannot edi the Munich Intermediate 1 section.
     * Then, we set the students' section  to the section, and we add the student to the sections' student list
     * we then update the repositories.
     * So with a find - check problem - add - update structure we will go through most of this Service
     * @param adminID admins ID
     * @param studentID students ID
     * @param sectionID as String! eventhough in Student we have ClassNames, we have to find and validate the section
     *                  In our case, sectionID is simply the name of the section
     * @throws SectionNotFoundException,StudentNotFoundException,AdminNotFoundException,IllegalAdminActionException because all of this can happen
     *
     * */
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

    /**
     * same game with the teacher, just with a different exception thrown this time!
     * */
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



    /**
     * The next two methods reassign students/teachers from their old section into the new one
     *
     * */
    public Student reassignStudentToSection(String adminID, String studentID, String sectionID) {
        try {
            Student student = deleteStudentFromSection(adminID, studentID, sectionID);
            Section section = findSection(sectionID);
            //change the section in the student
            student.setSection(section.getName());
            //add student to new class
            section.getStudents().put(studentID, student);
            //update section in sectionRepo
            sectionRepo.updateSection(section);
            //update student in studentRepo and return
            return studentRepo.update(student);
        } catch (SectionNotFoundException se) {
            throw new RuntimeException(se);
        }
    }


    public Teacher reassignTeacherToSection(String adminID, String teacherID, String sectionID) {
        try {
            Teacher teacher = deleteTeacherFromSection(adminID, teacherID, sectionID);
            Section section = findSection(sectionID);

            teacher.setSection(section);

            section.getTeachers().put(teacherID, teacher);

            sectionRepo.updateSection(section);

            return teacherRepo.update(teacher);
        } catch (SectionNotFoundException se) {
            throw new RuntimeException(se);
        }
    }


    /**
     * the next two methods remove the teacher/student from the system entirely.
     * This method needs special care because... well it's removing a student from the repository.
     * */
    public Student removeStudentFromSystem(String adminID, String studentID) {
        try {
            Administrator admin = findAdmin(adminID);
           Student student = findStudent(studentID);
           Section section = findSection(student.getSection().toString());
           checkPermissionToModify(admin,section);

        } catch (AdminNotFoundException | StudentNotFoundException | SectionNotFoundException |
                 IllegalAdminActionException e) {
            throw new RuntimeException(e);
        }

        return studentRepo.delete(studentID);
    }


    public Teacher removeTeacherFromSystem(String adminID, String teacherID) {

        try {
            Administrator admin = findAdmin(adminID);
            Teacher teacher = findTeacher(teacherID);
            Section section = findSection(teacher.getSection().toString());
            checkPermissionToModify(admin,section);

        } catch (AdminNotFoundException | SectionNotFoundException |
                 IllegalAdminActionException | TeacherNotFoundException e) {
            throw new RuntimeException(e);
        }

        return teacherRepo.delete(teacherID);
    }

    public Student deleteStudentFromSection(String adminID, String studentID, String sectionID) {
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
            student.setSection(null);
            return student;


        } catch (AdminNotFoundException | SectionNotFoundException | StudentNotFoundException |
                 IllegalAdminActionException e) {
            throw new RuntimeException(e);
        }


    }



    public Teacher deleteTeacherFromSection(String adminID, String teacherID, String sectionID) {
        try {
            Teacher teacher = findTeacher(teacherID);
            Administrator admin = findAdmin(adminID);
            Section section = findSection(sectionID);

            if (checkPermissionToModify(admin, section)) {
                throw new IllegalAdminActionException();
            }
            //remove student from old section
            sectionRepo
                    .findSectionByName(teacher.getSection().getName().toString())
                    .ifPresent(section1 -> section1.getTeachers().remove(teacherID, teacher));
            teacher.setSection(null);
            return teacher;


        } catch (AdminNotFoundException | SectionNotFoundException  |
                 IllegalAdminActionException | TeacherNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}
