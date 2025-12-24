package koodali.service;

import koodali.model.Section;
import koodali.repository.SectionRepository;
import koodali.service.exceptions.SectionNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SectionService {

    private final SectionRepository sectionRepository = new SectionRepository();

    public SectionService() {

    }

    /**
     * Get mapping
     * gets all sections
     */
    public List<Section> getAllSections() {
        return sectionRepository.getAllSections();
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
        Optional<Section> optionalSection = sectionRepository.findSectionByName(classId);
        if (optionalSection.isEmpty()) {
            throw new SectionNotFoundException();
        } else {
            return optionalSection.get();
        }

    }
    /**
     * since the amount of sections is constant, all we can do is update a given section
     * since we do not add a class externally, if you want to add a new section, please talk to Samhitha
     */
    public Section updateSection(Section section) {
        return sectionRepository.updateSection(section);
    }


    /**
     * deletes a Section from the koodali.repository, for example if two sections get merged
     * does NOT delete the enum entry, that has to be done manually!
     */
    public Section deleteSection(Section section) {

        return deleteSectionbyId(section.getName().name());

    }

    private Section deleteSectionbyId(String sectionName) {
        if (sectionRepository.containsSection(sectionName)) {
            return sectionRepository.deleteSection(sectionName);

        } else {
            throw new SectionNotFoundException();
        }

    }


}



