package work.course.notificationsystem.person.sender.dto;

import lombok.Builder;
import lombok.Data;
import work.course.notificationsystem.drone.dto.StationDTO;

@Data
@Builder
public class SenderDTO {
  private Long id;
  private String demoName;
  private StationDTO stationDTO;
}
