package service;

import model.Section;
import model.Teacher;
import repository.TeacherRepository;
import service.exceptions.SectionNotFoundException;
import service.exceptions.TeacherNotFoundException;

import java.util.List;
import java.util.Optional;

public class TeacherService extends PersonService<Teacher>{
    private final TeacherRepository teacherRepo;

    private final SectionService sectionService;


    public TeacherService(TeacherRepository teacherRepo) {
        super(teacherRepo);
        this.teacherRepo = teacherRepo;

        this.sectionService = new SectionService();
    }

    public Teacher findByID(String teacherID){
        return teacherRepo
                .findByID(teacherID)
                .orElseThrow(TeacherNotFoundException::new);
    }

    public Teacher findTeacherbyName(String name){
        return teacherRepo
                .findByName(name)
                .orElseThrow(TeacherNotFoundException::new);
    }


    public Teacher createTeacher(String teacherId, String firstName, String lastName, String sectionID) {
        try {

            Section section = sectionService.getSectionByID(sectionID);
            Teacher teacher = new Teacher(teacherId, firstName, lastName, section.getName());
            section.getTeachers().put(teacher.getID(), teacher);
            return teacherRepo.add(teacher);

        } catch (SectionNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean createTeachersFromList(List<Teacher> Teachers) {
        for (Teacher teacher : Teachers) {
            teacherRepo.add(teacher);
            sectionService
                    .getAllSections()
                    .stream()
                    .filter(
                            section -> section
                                    .getName()
                                    .equals(teacher.getSection()))
                    .findFirst()
                    .ifPresent(section -> section
                            .getTeachers()
                            .put(teacher.getID(), teacher));
        }
        return true;
    }


    public List<Teacher> listTeachersInSection(String sectionID) {
        try {
            Section section = sectionService.getSectionByID(sectionID);
            return section.getTeachers().values().stream().toList();
        } catch (SectionNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
