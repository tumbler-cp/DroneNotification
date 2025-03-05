package work.course.notificationsystem.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import work.course.notificationsystem.person.customer.dto.CustomerDTO;
import work.course.notificationsystem.person.sender.dto.SenderDTO;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
  private Long id;
  private Date createTime;
  private SenderDTO sender;
  private CustomerDTO customer;
  private List<OrderPositionDTO> positions;
}
