package koodali.service;

import koodali.model.Student;
import koodali.repository.LeaderboardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaderboardService {

    private final LeaderboardRepository leaderboardRepository;
    //HomewoerkExcelService service

    public LeaderboardService(LeaderboardRepository leaderboardRepository) {

        this.leaderboardRepository = leaderboardRepository;
    }

    public List<Student> fetchAllStudents(){
        List<String> ids = leaderboardRepository.findAll().stream().map(Student::getID).toList();
        return List.of();
        //TODO 3: bring in StudentService, create studentRepo and SectionService and pass them in constructor
        // either create method in StudentService getStudentsByListOfIDs or do a stream filter operation on studentService.getAll
        // return the filtered Students sorted by ID.
    }

    public List<Student> sortBy(String option){
        return List.of();
        //TODO 4: switch the options. Change into Enum if needed
        // Should only be following :
        // - descending/ascending by points
        // - descending ascending by ID
    }



}
