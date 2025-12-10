package repository;

import model.ClassNames;
import model.Section;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Component
public final class SectionRepository {

    private HashMap<ClassNames, Section> sections;

    public SectionRepository() {

    }

    /**
     * contains helper functions.
     * to be used in the service to throw up gang sig- oops, exceptions
     */
    public boolean containsSection(Section section) {
        return sections.containsValue(section);
    }

    public boolean containsSection(String className) {
        return sections.containsKey(ClassNames.valueOf(className));
    }

    /**
     * finds the section by it's name. The name is a className as String
     * When you call this in the SectionService, throw following exceptions :
     * InvalidArgumentException for if the class name is invalid
     * if(className.matches("[BDEILMNPX][ELNRUX]_[BIAN][UE_D][LG123V]"))
     * SectionNotFoundException if the section does not exist
     * GET mapping /sections/{name} with PathVariable String name
     *
     * @param className as String
     * @return Section that was found, or throw Exception
     */

    public Optional<Section> findSectionByName(String className) {
        return Optional.ofNullable(sections.get(ClassNames.valueOf(className)));
    }

    /**
     * returns all sections as sorted List
     * GET mapping /sections
     *
     * @return list of sections
     */
    public List<Section> getAllSections() {
        return sections.values().stream().sorted().toList();
    }

    /**
     * deletes a section.
     * DELETE mapping sections/{name}
     *
     * @param className as String
     * @return Section
     */
    public Section deleteSection(String className) {
        return sections.remove(ClassNames.valueOf(className));
    }

    /**
     * updates the sections map with a section that has changed, for example if a new teacher/student was added to the section
     * as we have a constant amount of sections, the key stays the name of the section,
     * and so all we have to do is "put".
     * PUT mapping /sections with RequestBody Section updatedSection;
     *
     * @param section Section
     * @return Section
     */

    public Section updateSection(Section section) {
        return sections.put(section.getName(), section);
    }


    public void setSections(HashMap<ClassNames, Section> sections) {
        this.sections = sections;
    }


}