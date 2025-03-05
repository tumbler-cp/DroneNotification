package work.course.notificationsystem.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import work.course.notificationsystem.security.model.User;
import work.course.notificationsystem.security.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;

  public User getCurrentUser() {
    Authentication authentication = SecurityContextHolder.getContext()
            .getAuthentication();
    return userRepository.findUByUsername(authentication.getName());
  }
}
