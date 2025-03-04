package work.course.notificationsystem.security.dto.response;

import lombok.Builder;
import lombok.Data;
import work.course.notificationsystem.security.model.enums.Role;

@Data
@Builder
public class UserDto {
  private Long id;
  private String username;
  private String email;
  private String phone;
  private Role role;
}
