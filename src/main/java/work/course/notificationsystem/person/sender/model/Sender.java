package work.course.notificationsystem.person.sender.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import work.course.notificationsystem.drone.model.DroneStation;
import work.course.notificationsystem.security.model.User;
import work.course.notificationsystem.shop.model.WareHouse;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sender {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String demoName;

  @NotNull
  @MapsId
  @JoinColumn(name = "user_id", unique = true)
  @OneToOne(fetch = FetchType.EAGER)
  private User user;

  @ManyToOne(fetch = FetchType.EAGER)
  private DroneStation droneStation;

  @OneToOne(mappedBy = "sender", orphanRemoval = true)
  private WareHouse wareHouse;
}
