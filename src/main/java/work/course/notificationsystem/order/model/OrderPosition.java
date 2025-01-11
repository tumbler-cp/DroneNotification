package work.course.notificationsystem.order.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import work.course.notificationsystem.shop.model.Good;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderPosition {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Integer quantity;

  @ManyToOne
  @JoinColumn(name = "order_id", nullable = false)
  private Order order;

  @ManyToOne
  @JoinColumn(name = "good_id", nullable = false)
  private Good good;

}
