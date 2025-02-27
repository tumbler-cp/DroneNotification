package work.course.notificationsystem.order.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import work.course.notificationsystem.drone.model.Drone;
import work.course.notificationsystem.person.customer.model.Customer;
import work.course.notificationsystem.person.sender.model.Sender;

import java.util.Date;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "app_orders")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private Date creationTime;

  @NotNull
  @ManyToOne
  private Sender sender;

  @NotNull
  @ManyToOne
  private Customer customer;

  @OneToOne
  private Drone drone;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<OrderPosition> positions;

}
