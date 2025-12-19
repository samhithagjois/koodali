package koodali.service;

import koodali.repository.*;
import org.springframework.stereotype.Service;

@Service
public class LeaderboardService {

    private final LeaderboardRepository leaderboardRepository = new LeaderboardRepository();
    //HomewoerkExcelService service

    public LeaderboardService(){

    }

    //TODO : following methods :
    // 1: fetch all students
    // 2: sort by highest points


}
