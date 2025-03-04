package work.course.notificationsystem.drone.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import work.course.notificationsystem.drone.dto.StationDTO;
import work.course.notificationsystem.drone.dto.StationTokenDTO;
import work.course.notificationsystem.drone.service.StationService;
import work.course.notificationsystem.drone.service.StationSubscriptionService;
import work.course.notificationsystem.person.sender.dto.SenderDTO;
import work.course.notificationsystem.person.sender.model.Sender;

import java.util.List;

@RestController
@RequestMapping("/station")
@RequiredArgsConstructor
public class StationSubscriptionController {

  private final StationSubscriptionService stationSubscriptionService;
  private final StationService stationService;

  @GetMapping("/all")
  public List<StationDTO> getAllStations() {
    return stationService.getStations();
  }

  @PostMapping("/{id}/generate-token")
  public StationTokenDTO generateStationToken(@PathVariable Long id) {
    return StationTokenDTO.builder()
        .stationToken(stationSubscriptionService.generateStationToken(id))
        .build();
  }

  @PostMapping("/{senderId}/bind")
  public ResponseEntity<SenderDTO> bindSenderToStation(@PathVariable Long senderId,
                                                       @RequestBody StationTokenDTO stationTokenDTO) {
    Sender sender = stationSubscriptionService.bindSenderToStation(senderId, stationTokenDTO.getStationToken());

    StationDTO stationDTO = null;
    if (sender.getDroneStation() != null) {
      stationDTO = StationDTO.builder()
          .id(sender.getDroneStation()
              .getId())
          .address(sender.getDroneStation()
              .getAddress())
          .build();
    }

    SenderDTO senderDTO = SenderDTO.builder()
        .id(sender.getId())
        .demoName(sender.getDemoName())
        .stationDTO(stationDTO)
        .build();

    return ResponseEntity.ok(senderDTO);
  }
}