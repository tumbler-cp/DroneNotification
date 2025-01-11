package work.course.notificationsystem.shop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import work.course.notificationsystem.order.model.OrderPosition;

import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Good {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private Float weight;
  private String description;

  @OneToMany(mappedBy = "good", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<OrderPosition> order;

}
