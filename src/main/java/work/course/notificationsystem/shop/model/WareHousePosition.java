package work.course.notificationsystem.shop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WareHousePosition {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Integer quantity;

  @ManyToOne
  @JoinColumn(name = "warehouse_id", nullable = false)
  private WareHouse wareHouse;

  @ManyToOne
  @JoinColumn(name = "good_id", nullable = false)
  private Good good;
}
