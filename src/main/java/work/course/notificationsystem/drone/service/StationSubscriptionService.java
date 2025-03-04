package work.course.notificationsystem.drone.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import work.course.notificationsystem.drone.model.DroneStation;
import work.course.notificationsystem.drone.repository.DroneStationRepository;
import work.course.notificationsystem.person.sender.model.Sender;
import work.course.notificationsystem.person.sender.repository.SenderRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StationSubscriptionService {

  private final StationTokenService stationTokenService;
  private final DroneStationRepository droneStationRepository;
  private final SenderRepository senderRepository;

  public Sender bindSenderToStation(Long senderId, String token) {
    var stationUUID = stationTokenService.extractStationUUID(token);
    DroneStation station = droneStationRepository.getDroneStationByUuid(UUID.fromString(stationUUID));

    Sender sender = senderRepository.findById(senderId)
        .orElseThrow(() -> new IllegalArgumentException("Sender not found"));
    if (station == null || sender == null) {
      throw new IllegalArgumentException("Station or Sender not found");
    }
    sender.setDroneStation(station);
    senderRepository.save(sender);
    return sender;
  }

  public String generateStationToken(Long stationId) {
    var station = droneStationRepository.findById(stationId).orElseThrow(() -> new IllegalArgumentException("Station not found"));
    return stationTokenService.generateToken(station);
  }

}
