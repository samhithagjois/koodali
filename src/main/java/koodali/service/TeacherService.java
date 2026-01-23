package koodali.service;

import koodali.model.Section;
import koodali.model.Teacher;
import koodali.model.dto.teacherDTO.CreateTeacherDTO;
import koodali.model.dto.teacherDTO.SectionTeacherOverviewDTO;
import koodali.model.dto.teacherDTO.TeacherOverviewDTO;
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

private SectionTeacherOverviewDTO teacherToSectionTeacherOverviewDTO(Teacher teacher){
        return new SectionTeacherOverviewDTO(teacher.getID(),teacher.getFirstName()+teacher.getLastName(),teacher.getJoinDate());
}

    public Teacher findByID(String teacherID) {
        return teacherRepo
                .findById(teacherID)
                .orElseThrow(TeacherNotFoundException::new);
    }


    public Teacher findTeacherbyName(String firstName, String lastName) {
        return teacherRepo
                .findByFirstNameIgnoreCaseOrLastNameIgnoreCase(firstName, lastName)
                .orElseThrow(TeacherNotFoundException::new);
    }

    public Teacher createTeacher(Teacher teacher) {
        return teacherRepo.save(teacher);
    }

    public Teacher createTeacherfromCreateTeacherDTO(CreateTeacherDTO dto) {
        return teacherRepo.save(new Teacher(dto.firstName(),dto.lastName()));
    }

    public TeacherOverviewDTO teacherToTeacherOverDTO(Teacher teacher){
        String sectionsOfTeacher = teacher.getSections().stream().reduce(String::concat).orElseThrow();
        return new TeacherOverviewDTO(teacher.getID(),teacher.getFirstName(), teacher.getLastName(), teacher.getJoinDate(),sectionsOfTeacher);
    }


    public Teacher createTeacher(String teacherId, String firstName, String lastName, List<String> sectionIDs) {

        List<String> teacherClasses = new ArrayList<>();
        for (String s : sectionIDs) {
            Section sectionByID = sectionService.getSectionByName(s);
            teacherClasses.add(sectionByID.getName());

        }


        Teacher teacher = new Teacher(teacherId, firstName, lastName, teacherClasses);

        for (String name : teacherClasses) {
            sectionService.getSectionByName(name).getTeachers().put(teacher.getID(), teacher);
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


    public List<SectionTeacherOverviewDTO> listTeachersInSection(String sectionID) {

        Section section = sectionService.getSectionByName(sectionID);
        return section.getTeachers().values().stream().map(this::teacherToSectionTeacherOverviewDTO).toList();

    }

}
