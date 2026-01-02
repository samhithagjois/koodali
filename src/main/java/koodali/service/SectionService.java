package koodali.service;

import koodali.model.Section;
import koodali.model.Student;
import koodali.model.Teacher;
import koodali.repository.SectionRepository;
import koodali.service.exceptions.InvalidSectionNameException;
import koodali.service.exceptions.SectionNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class SectionService {

    private final SectionRepository sectionRepository;

    public SectionService(SectionRepository sectionRepository) {

        this.sectionRepository = sectionRepository;
    }

    /**
     * Get mapping
     * gets all sections
     */
    public List<Section> getAllSections() {
        return sectionRepository.findAll();
    }

    public SectionRepository getSectionRepository() {
        return sectionRepository;
    }

    /**
     * gets section by its id and throws a SectionNotFoundException if it doesn't work
     *
     * @param classId class ID
     * @return Section
     * @throws SectionNotFoundException "didn't find section"
     */
    public Section getSectionByID(String classId) {
      return  Optional.of(sectionRepository.getReferenceById(classId)).orElseThrow(SectionNotFoundException::new);
    }

    public Section updateSection(Section section) {
        return sectionRepository.save(section);
    }

    private boolean validateName(String sectionName){
        return sectionName.matches("[A-Z][A-Z]_[BIA][END]T*([GV]|[_0-9])*");
    }

    public Section createSection(Section section){
        if (validateName(section.getName())){
            return sectionRepository.save(section);
        }else{
            throw new InvalidSectionNameException();
        }

    }

    public Section createSection(String name, List<Student> students, List<Teacher> teachers, List<LocalDateTime> schedule){
        if (!validateName(name)){
            throw new InvalidSectionNameException();
        }
        HashMap<String,Student> s = new HashMap<>();
        students.forEach(student -> s.put(student.getID(),student));

        HashMap<String,Teacher> t = new HashMap<>();
        teachers.forEach(teacher -> t.put(teacher.getID(),teacher));
        return sectionRepository.save(new Section(name,s,t,schedule));
    }

    /**
     * deletes a Section from the koodali.repository, for example if two sections get merged
     * does NOT delete the enum entry, that has to be done manually!
     */
    public Section deleteSection(Section section) {

        return deleteSectionbyId(section.getName());

    }

    private Section deleteSectionbyId(String sectionName) {
        if (sectionRepository.existsById(sectionName)) {
            Section s = sectionRepository.getReferenceById(sectionName);
            sectionRepository.delete(s);
            return s;

        } else {
            throw new SectionNotFoundException();
        }

    }

    //addStudents
    //removeStudents -> warn when students.amount <=1 that it is better to delete the section

}



