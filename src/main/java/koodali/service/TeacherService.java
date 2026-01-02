package koodali.service;

import koodali.model.Section;
import koodali.model.Teacher;
import koodali.repository.TeacherRepository;
import koodali.service.exceptions.TeacherNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherService extends PersonService<Teacher> {
    private final TeacherRepository teacherRepo;

    private final SectionService sectionService;


    public TeacherService(TeacherRepository teacherRepo, SectionService sectionService) {
        super(teacherRepo);
        this.teacherRepo = teacherRepo;

        this.sectionService = sectionService;
    }

    public Teacher findByID(String teacherID) {
        return teacherRepo
                .findById(teacherID)
                .orElseThrow(TeacherNotFoundException::new);
    }


    public Teacher findTeacherbyName(String firstName,String lastName) {
        return teacherRepo
                .findByFirstNameIgnoreCaseOrLastNameIgnoreCase(firstName,lastName)
                .orElseThrow(TeacherNotFoundException::new);
    }

    public Teacher createTeacher(Teacher teacher){
        return teacherRepo.save(teacher);
    }


    public Teacher createTeacher(String teacherId, String firstName, String lastName, List<String> sectionIDs) {

        List<String> teacherClasses = new ArrayList<>();
        for (String s:sectionIDs) {
            Section sectionByID = sectionService.getSectionByID(s);
            teacherClasses.add(sectionByID.getName());

        }


        Teacher teacher = new Teacher(teacherId, firstName, lastName, teacherClasses);

        for (String name:teacherClasses ) {
            sectionService.getSectionByID(name).getTeachers().put(teacher.getID(),teacher);
        }

        return teacherRepo.save(teacher);

    }

    public boolean createTeachersFromList(List<Teacher> Teachers) {
        for (Teacher teacher : Teachers) {
            teacherRepo.save(teacher);
            sectionService
                    .getAllSections()
                    .stream()
                    .filter(
                            section -> teacher.getSections().contains(section.getName()))
                    .findFirst()
                    .ifPresent(section -> section
                            .getTeachers()
                            .put(teacher.getID(), teacher));
        }
        return true;
    }


    public List<Teacher> listTeachersInSection(String sectionID) {

        Section section = sectionService.getSectionByID(sectionID);
        return section.getTeachers().values().stream().toList();

    }

}
