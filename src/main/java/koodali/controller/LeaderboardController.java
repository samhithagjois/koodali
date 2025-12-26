package koodali.controller;

import koodali.model.Student;
import koodali.service.LeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class LeaderboardController {

    private final LeaderboardService leaderboardService;

    @Autowired
    public LeaderboardController(LeaderboardService leaderboardService) {
        this.leaderboardService = leaderboardService;
    }

    public LeaderboardService getLeaderboardService() {
        return leaderboardService;
    }

    //TODO : following methods :
    // 1: fetch all students
    // 2: sort by highest points
    // 3 : filter by section


}
