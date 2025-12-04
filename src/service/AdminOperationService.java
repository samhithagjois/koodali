package service;

import repository.AdminRepository;
import repository.SectionRepository;
import repository.StudentRepository;
import repository.TeacherRepository;

import model.*;
import service.exceptions.*;

public class AdminOperationService {

    private final StudentRepository studentRepo = new StudentRepository();
    private final TeacherRepository teacherRepo = new TeacherRepository();

    private final AdminRepository adminRepo = new AdminRepository();

    private final SectionRepository sectionRepo = new SectionRepository();

    public AdminOperationService(){

    }

    /**
     * checks the permission of the admin.
     * So the admin permission list is two  letters and corresponds with the first two letters of the section
     * we compare these two and if its not equal we throw a IllegalAdminActionException.
     * This is an internal method. I don't think this should be visible outside
     * @param admin administrator
     * @param section Section to check
     * @throws IllegalAdminActionException if for example a Munich admin tries changing Ingolstadt
     * @return true or false
     *
     * */
    public boolean checkPermissionToModify(Administrator admin, Section section) throws IllegalAdminActionException {
        String area = section.getName().toString().substring(0,1);
        for (AdminPermissions permission:admin.getPermissions()  ) {
            if(!permission.toString().equals(area)){
                throw new IllegalAdminActionException();
            }
        }
        return true;
    }

    public Student addStudentToSection (String adminID, String studentID, String sectionID) throws
            StudentNotFoundException,
            SectionNotFoundException,
            AdminNotFoundException,
            IllegalAdminActionException {

        Person admin = adminRepo
                .findByID(adminID)
                .orElseThrow(AdminNotFoundException::new);
        Person student = studentRepo
              .findByID(studentID)
              .orElseThrow(StudentNotFoundException::new);

      Section section = sectionRepo
              .findSectionByName(sectionID)
              .orElseThrow(SectionNotFoundException::new);

      if(student instanceof Student s && admin instanceof Administrator a){
          if(checkPermissionToModify(a, section)){
             throw new IllegalAdminActionException();
          }
          s.setSection(section.getName());
          section.getStudents().put(s.getID(),s);
          studentRepo.update(s);
          sectionRepo.updateSection(section);

      }else{
          throw new RuntimeException("imposter go brrr");
      }
      return s;
    }


    public Teacher addTeacherToSection (String adminID, String teacherID, String sectionID) throws
             SectionNotFoundException,
            AdminNotFoundException,
            IllegalAdminActionException,
            TeacherNotFoundException {

        Person admin = adminRepo
                .findByID(adminID)
                .orElseThrow(AdminNotFoundException::new);
        Person teacher = teacherRepo
                .findByID(teacherID)
                .orElseThrow(TeacherNotFoundException::new);

        Section section = sectionRepo
                .findSectionByName(sectionID)
                .orElseThrow(SectionNotFoundException::new);

        if(teacher instanceof Teacher t && admin instanceof Administrator a){
            if(checkPermissionToModify(a, section)){
                throw new IllegalAdminActionException();
            }
            t.setSection(section);
            section.getTeachers().put(t.getID(),t);
            teacherRepo.update(t);
            sectionRepo.updateSection(section);

        }else{
            throw new RuntimeException("imposter go brrr");
        }
        return t;
    }


    public Student reassignStudentToSection(String adminID, String studentID,String sectionID){
        //TODO!
        //TODO : delete student from section. delete section from student. add student to new section. add section to student
        // save the respective students/sections.
        return null;
    }



    //reassignStudentToSection(Student student, Section section)
    //deleteStudentFromSection(Student student, Section section)
    //removeStudentFromSystem(Student student)

    //addCoTeacherToSection(Teacher teacher, Section section)
    //deleteTeacherFromSection(Teacher teacher, Section section)
    //removeTeacherFromSystem(Teacher teacher)

}
