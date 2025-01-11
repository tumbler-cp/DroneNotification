package work.course.notificationsystem.drone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import work.course.notificationsystem.drone.model.DroneStation;

@Repository
public interface DroneStationRepository extends JpaRepository<DroneStation, Long> {

}
