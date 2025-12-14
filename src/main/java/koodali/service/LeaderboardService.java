package koodali.service;

import koodali.repository.*;
import org.springframework.stereotype.Service;

@Service
public class LeaderboardService {

    private final LeaderboardRepository leaderboardRepository = new LeaderboardRepository();

    public LeaderboardService(){

    }

}
