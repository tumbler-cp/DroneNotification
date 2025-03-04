package work.course.notificationsystem.shop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import work.course.notificationsystem.drone.model.DroneStation;
import work.course.notificationsystem.person.sender.model.Sender;

import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WareHouse {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @ManyToOne(fetch = FetchType.EAGER)
  private DroneStation station;

  @NotNull
  @JoinColumn(name = "sender_id", unique = true)
  @OneToOne(fetch = FetchType.EAGER)
  private Sender sender;

  @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<WareHousePosition> positions;

}
