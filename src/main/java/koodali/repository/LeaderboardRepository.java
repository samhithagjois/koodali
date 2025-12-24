package koodali.repository;

import koodali.model.Student;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class LeaderboardRepository {

    private final HashMap<String, Integer> pointsMap;

    public LeaderboardRepository() {
        this.pointsMap = new HashMap<>();
    }

    public HashMap<String, Integer> getPointsMap() {
        return pointsMap;
    }

    public void addToLeaderboard(Student student, int newPoints) {
        pointsMap.put(student.getID(), student.getHomeworkLeaderBoardScore() + newPoints);
    }


    public void getSortedbyID() {

    }


}
