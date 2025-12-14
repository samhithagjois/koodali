package repository;

import java.util.HashMap;
import java.util.List;

public class LeaderboardRepository {

   public HashMap<String, Integer> getPointsMap() {
      return pointsMap;
   }

   private final HashMap<String,Integer> pointsMap;

   public LeaderboardRepository() {
      this.pointsMap = new HashMap<>();
   }

   public List<String> getSorted(){
      return pointsMap.keySet().stream().sorted().toList();
   }


}
