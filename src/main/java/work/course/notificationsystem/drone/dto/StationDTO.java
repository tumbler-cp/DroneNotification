package work.course.notificationsystem.drone.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StationDTO {
  private Long id;
  private String address;
}
