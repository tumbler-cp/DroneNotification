package work.course.notificationsystem.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import work.course.notificationsystem.drone.dto.StationDTO;
import work.course.notificationsystem.person.sender.dto.SenderDTO;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WareHouseDTO {
  private Long id;
  private StationDTO station;
  private SenderDTO sender;
}
