package work.course.notificationsystem.drone.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import work.course.notificationsystem.drone.model.enums.DroneType;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Drone {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Enumerated(EnumType.STRING)
  private DroneType droneType;

  @NotNull
  @ManyToOne(fetch = FetchType.EAGER)
  private DroneStation station;

}
