package repository;

import model.ClassNames;
import model.Section;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Component
public final class SectionRepository {

    private static HashMap<ClassNames,Section> sections;

    public SectionRepository(){

    }

    /**
     * finds the section by it's name. The name is a className,
     * @param className as String
     * @return Section that was found, or throw Exception
     */

    public static Section findSectionByName(String className) throws SectionNotFoundException {
        if(className.matches("[BDEILMNPX][ELNRUX]_[BIAN][UE_D][LG123V]")){
            return sections.get(ClassNames.valueOf(className));
        }else{
            throw new SectionNotFoundException();
        }
    }
    /**
     * returns all sections as sorted List
     *
     * @return list of sections
     */
    public static List<Section> getAllSections(){
       return sections.values().stream().sorted().toList();
    }
    //deleteSection
    public static void deleteSection(String className) throws SectionNotFoundException {
        if(className.matches("[BDEILMNPX][ELNRUX]_[BIAN][UE_D][LG123V]")){
            sections.remove(ClassNames.valueOf(className));
        }else{
            throw new SectionNotFoundException();
        }
    }
    //addSection
    public static void addSection(String className,Section section){
        if(className.matches("[BDEILMNPX][ELNRUX]_[BIAN][UE_D][LG123V]")){
            try {
                ClassNames names = ClassNames.valueOf(className);
            }catch(IllegalArgumentException i){
                i.printStackTrace();
            }
            sections.put(ClassNames.valueOf(className),section);
        }

    }

}
