package work.course.notificationsystem.person.sender.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import work.course.notificationsystem.drone.model.DroneStation;

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
  @OneToOne(fetch = FetchType.EAGER)
  private Sender sender;

}
