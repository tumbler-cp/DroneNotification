package work.course.notificationsystem.drone.model;

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
public class DroneStation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

}
