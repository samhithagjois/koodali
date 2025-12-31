package koodali.service;

import koodali.model.Section;
import koodali.repository.SectionRepository;
import koodali.service.exceptions.SectionNotFoundException;
import org.springframework.stereotype.Service;

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
    /**
     * since the amount of sections is constant, all we can do is update a given section
     * since we do not add a class externally, if you want to add a new section, please talk to Samhitha
     */
    public Section updateSection(Section section) {
        return sectionRepository.save(section);
    }


    /**
     * deletes a Section from the koodali.repository, for example if two sections get merged
     * does NOT delete the enum entry, that has to be done manually!
     */
    public Section deleteSection(Section section) {

        return deleteSectionbyId(section.getName().name());

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



