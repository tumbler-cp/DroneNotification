package work.course.notificationsystem.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import work.course.notificationsystem.shop.dto.GoodDTO;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderPositionDTO {
  private GoodDTO good;
  private Integer quantity;
}
