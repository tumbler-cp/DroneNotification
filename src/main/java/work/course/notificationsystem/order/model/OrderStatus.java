package work.course.notificationsystem.order.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import work.course.notificationsystem.order.model.enums.OrderStatusStamp;

import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatus {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Enumerated(EnumType.STRING)
  private OrderStatusStamp status;

  private Date createTime;
  private Date updateTime;
  private String estimatedTime;

  @NotNull
  @OneToOne(fetch = FetchType.EAGER)
  private Order order;

}
