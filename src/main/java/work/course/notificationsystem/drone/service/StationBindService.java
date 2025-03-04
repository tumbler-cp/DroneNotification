package work.course.notificationsystem.drone.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import work.course.notificationsystem.drone.model.DroneStation;
import work.course.notificationsystem.drone.repository.DroneStationRepository;
import work.course.notificationsystem.person.sender.service.SenderService;
import work.course.notificationsystem.person.sender.model.Sender;
import work.course.notificationsystem.person.sender.repository.SenderRepository;
import work.course.notificationsystem.shop.model.WareHouse;
import work.course.notificationsystem.shop.repository.WareHouseRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StationBindService {

  private final StationTokenService stationTokenService;
  private final DroneStationRepository droneStationRepository;
  private final SenderRepository senderRepository;
  private final WareHouseRepository wareHouseRepository;
  private final SenderService senderService;
  private final StationService stationService;

  @Transactional
  public Sender bindSenderToStation(Long senderId, String token) {
    var stationUUID = stationTokenService.extractStationUUID(token);
    DroneStation station = droneStationRepository.getDroneStationByUuid(UUID.fromString(stationUUID));

    Sender sender = senderService.findById(senderId);
    if (station == null || sender == null) {
      throw new IllegalArgumentException("Station or Sender not found");
    }
    sender.setDroneStation(station);
    senderRepository.save(sender);

    WareHouse wareHouse = WareHouse.builder().sender(sender).station(station).build();
    wareHouseRepository.save(wareHouse);

    return sender;
  }

  @Transactional
  public Sender unbindSenderFromStation(Long senderId) {
    var sender = senderService.findById(senderId);
    sender.setDroneStation(null);
    sender.getWareHouse().setSender(null);
    wareHouseRepository.save(sender.getWareHouse());
    sender.setWareHouse(null);
    senderRepository.save(sender);
    return sender;
  }

  public String generateStationToken(Long stationId) {
    var station = stationService.findById(stationId);
    return stationTokenService.generateToken(station);
  }

}
