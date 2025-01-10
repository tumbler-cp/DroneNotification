package work.course.notificationsystem.security.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthToken {
  private String token;
}
