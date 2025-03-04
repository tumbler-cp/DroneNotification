package work.course.notificationsystem.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GoodDTO {
  private Long id;
  private String name;
  private Float weight;
  private String description;
  private String senderDemoName;
}
