package service;

import model.ClassNames;
import model.Section;
import repository.SectionRepository;

import java.util.List;
import java.util.Optional;

public class SectionService {

    private final SectionRepository sectionRepository = new SectionRepository();

    public SectionService(){

    }

    //getAllSections
    //updateSection
    //deleteSection
    //addNewSection

    /**
     * Get mapping
     * */
    public List<Section> getAllSections(){
        return SectionRepository.getAllSections();
    }

    public SectionRepository getSectionRepository() {
        return sectionRepository;
    }

    /**
     * gets section by its id and throws a SectionNotFoundException if it doesn't work
     * */
    public Section getSectionByID(String classId) throws SectionNotFoundException {
        Optional<Section> optionalSection = SectionRepository.findSectionByName(classId);
        if(optionalSection.isEmpty()){
            throw new SectionNotFoundException();
        }else{
            return optionalSection.get();
        }

    }

    /**
     * since the amount of sections is constant, all we can do is update a given section
     * */
    public Section updateSection(Section section){
        return SectionRepository.updateSection(section);
    }

    public Section updateSection(String classId){
        Section s = null;
        try{
            s = getSectionByID(classId);
        } catch (SectionNotFoundException e) {
            e.printStackTrace();
        }
        return SectionRepository.updateSection(s);
    }

    /**
     * deletes a Section from the repository, for example if two sections get merged
     * does NOT delete the enum entry, that has to be done manually
     */
    public Section deleteSection(Section section){
        try {
            return deleteSectionbyId(section.getName().name());
        } catch (SectionNotFoundException e) {

            throw new RuntimeException(e);
        }
    }

    public Section deleteSectionbyId(String sectionName) throws SectionNotFoundException {
        if (SectionRepository.containsSection(sectionName)) {
            return SectionRepository.deleteSection(sectionName);
        }else{
            throw new SectionNotFoundException();
        }

    }



}



