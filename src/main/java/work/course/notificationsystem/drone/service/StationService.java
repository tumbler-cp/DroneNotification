package work.course.notificationsystem.drone.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import work.course.notificationsystem.drone.dto.StationDTO;
import work.course.notificationsystem.drone.repository.DroneStationRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StationService {

  private final DroneStationRepository droneStationRepository;

  public List<StationDTO> getStations() {
    var retA = droneStationRepository.findAll();
    List<StationDTO> retB = new ArrayList<>();
    for (var station : retA) {
      retB.add(StationDTO.builder()
          .id(station.getId())
          .address(station.getAddress())
          .build());
    }
    return retB;
  }

}
