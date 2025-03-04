package work.course.notificationsystem.person.customer.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import work.course.notificationsystem.person.sender.model.Sender;
import work.course.notificationsystem.security.model.User;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String address;

  @NotNull
  @MapsId
  @JoinColumn(name = "user_id")
  @OneToOne(fetch = FetchType.EAGER)
  private User user;

}
