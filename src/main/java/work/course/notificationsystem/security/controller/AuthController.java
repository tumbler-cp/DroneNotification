package work.course.notificationsystem.security.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import work.course.notificationsystem.security.dto.request.AuthRequest;
import work.course.notificationsystem.security.dto.response.AuthToken;
import work.course.notificationsystem.security.dto.response.UserDto;
import work.course.notificationsystem.security.model.User;
import work.course.notificationsystem.security.repository.UserRepository;
import work.course.notificationsystem.security.service.AuthenticationService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthenticationService authenticationService;
  private final UserRepository userRepository;

  @PostMapping("/register")
  public ResponseEntity<AuthToken> register(@RequestBody AuthRequest authRequest) {
    return ResponseEntity.ok(authenticationService.register(authRequest));
  }

  @PostMapping("/login")
  public ResponseEntity<AuthToken> login(@RequestBody AuthRequest authRequest) {
    return ResponseEntity.ok(authenticationService.login(authRequest));
  }

  @GetMapping("/me")
  public UserDto me() {
    User user = userRepository
        .findUByUsername(SecurityContextHolder
            .getContext()
            .getAuthentication()
            .getName());
    return UserDto
        .builder()
        .id(user.getId())
        .username(user.getUsername())
        .role(user.getRole())
        .build();
  }

}
