package work.course.notificationsystem.drone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import work.course.notificationsystem.drone.model.DroneStation;

import java.util.UUID;

@Repository
public interface DroneStationRepository extends JpaRepository<DroneStation, Long> {

  DroneStation getDroneStationByUuid(UUID uuid);
}
