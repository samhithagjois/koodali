package service;

import model.Person;
import model.Section;
import model.Teacher;
import repository.SectionRepository;
import repository.TeacherRepository;
import service.exceptions.SectionNotFoundException;
import service.exceptions.TeacherNotFoundException;

import java.util.List;

public class TeacherService {
    private final TeacherRepository teacherRepo = new TeacherRepository();

    private final SectionRepository sectionRepo = new SectionRepository();

    public TeacherService() {

    }

    public Teacher findTeacherbyID(String teacherID) throws TeacherNotFoundException {
        Person teacher = teacherRepo
                .findByID(teacherID)
                .orElseThrow(TeacherNotFoundException::new);
        return (Teacher) teacher;
    }

    public Teacher findTeacherbyName(String name) throws TeacherNotFoundException {
        Person teacher = teacherRepo
                .findByName(name)
                .orElseThrow(TeacherNotFoundException::new);
        return (Teacher) teacher;
    }

    private Section findSection(String sectionName) throws SectionNotFoundException {
        return sectionRepo
                .findSectionByName(sectionName)
                .orElseThrow(SectionNotFoundException::new);
    }

    public Teacher createTeacher(String teacherId, String firstName, String lastName, String sectionID) {
        try {

            Section section = findSection(sectionID);
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
            sectionRepo
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


    public Teacher updateTeacher(Teacher teacher) {
        return teacherRepo.update(teacher);
    }


    public List<Teacher> listTeachersInSection(String sectionID) {
        try {
            Section section = findSection(sectionID);
            return section.getTeachers().values().stream().toList();
        } catch (SectionNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepo.getAll();
    }
}
