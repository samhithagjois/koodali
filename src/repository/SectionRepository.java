package repository;

import model.ClassNames;
import model.Section;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Component
public final class SectionRepository {

    private static HashMap<ClassNames, Section> sections;

    public SectionRepository() {

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

    public static Optional<Section> findSectionByName(String className) {
        return Optional.of(sections.get(ClassNames.valueOf(className)));
    }

    /**
     * returns all sections as sorted List
     * GET mapping /sections
     *
     * @return list of sections
     */
    public static List<Section> getAllSections() {
        return sections.values().stream().sorted().toList();
    }
    //deleteSection

    /**
     * deletes a section.
     * DELETE mapping sections/{name}
     */
    public static void deleteSection(String className) {

        sections.remove(ClassNames.valueOf(className));

    }
    //addSection

    /**
     * adds a section with its key, the class name, to the map
     * check beforehand with following regex  if(className.matches("[BDEILMNPX][ELNRUX]_[BIAN][UE_D][LG123V]")){
     * if the string is invalid and throw an IllegalArgumentException
     * POST mapping /sections with RequestBody Student student
     *
     * @param className as String
     * @param section   Section
     */
    public static void addSection(String className, Section section) {

        ClassNames name = ClassNames.valueOf(className);
        sections.put(name, section);

    }
    ///updateSection
    /**
     * updates the sections map with a section that has changed, for example if a new teacher/student was added to the section
     * as we have a constant amount of sections, the key stays the name of the section,
     * and so all we have to do is "put".
     * PUT mapping /sections with RequestBody Section updatedSection;
     * */

    public static void updateSection(Section section) {
        sections.put(section.getName(), section);
    }

}