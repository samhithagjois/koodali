package service;

import model.Section;
import repository.SectionRepository;
import service.exceptions.SectionNotFoundException;

import java.util.List;
import java.util.Optional;

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
     * deletes a Section from the repository, for example if two sections get merged
     * does NOT delete the enum entry, that has to be done manually!
     */
    public Section deleteSection(Section section) {
        try {
            return deleteSectionbyId(section.getName().name());
        } catch (SectionNotFoundException e) {

            throw new RuntimeException(e);
        }
    }

    public Section deleteSectionbyId(String sectionName){
        if (sectionRepository.containsSection(sectionName)) {
            return sectionRepository.deleteSection(sectionName);

        } else {
            throw new SectionNotFoundException();
        }

    }


}



