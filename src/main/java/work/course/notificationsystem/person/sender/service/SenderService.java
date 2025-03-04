package work.course.notificationsystem.person.sender.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import work.course.notificationsystem.drone.dto.StationDTO;
import work.course.notificationsystem.person.sender.dto.SenderDTO;
import work.course.notificationsystem.person.sender.model.Sender;
import work.course.notificationsystem.person.sender.repository.SenderRepository;

@Service
@RequiredArgsConstructor
public class SenderService {

  private final SenderRepository senderRepository;

  public Sender findById(Long id) {
    return senderRepository.findById(id)
            .orElseThrow(RuntimeException::new);
  }

  public SenderDTO toDTO(Sender sender) {
    var ret = SenderDTO.builder()
            .id(sender.getId())
            .demoName(sender.getDemoName())
            .stationDTO(null)
            .build();
    if (sender.getDroneStation() != null) {
      ret.setStationDTO(StationDTO.builder()
              .id(sender.getDroneStation()
                      .getId())
              .address(sender.getDroneStation()
                      .getAddress())
              .build());
    }
    return ret;
  }

}
