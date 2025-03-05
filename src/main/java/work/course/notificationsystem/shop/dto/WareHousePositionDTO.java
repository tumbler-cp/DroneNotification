package work.course.notificationsystem.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WareHousePositionDTO {
  private Integer quantity;
  private GoodDTO good;
}
