package koodali.repository;

import koodali.model.HomeworkPointsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeworkPointsRepository extends JpaRepository<HomeworkPointsEntity,Integer> {
}
