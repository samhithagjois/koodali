package koodali.repository;

import koodali.model.Student;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class LeaderboardRepository {

   public HashMap<String, Integer> getPointsMap() {
      return pointsMap;
   }

   private final HashMap<String,Integer> pointsMap;

   public LeaderboardRepository() {
      this.pointsMap = new HashMap<>();
   }

   public void addToLeaderboard(Student student, int newPoints){
      pointsMap.put(student.getID(),student.getHomeworkLeaderBoardScore()+newPoints);
   }



   public void getSortedbyID(){

   }




}
