package work.course.notificationsystem.security.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work.course.notificationsystem.security.dto.request.AuthRequest;
import work.course.notificationsystem.security.dto.response.AuthToken;
import work.course.notificationsystem.security.service.AuthenticationService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthenticationService authenticationService;

  @PostMapping("/register")
  public ResponseEntity<AuthToken> register(@RequestBody AuthRequest authRequest) {
    return ResponseEntity.ok(authenticationService.register(authRequest));
  }

  @PostMapping("/login")
  public ResponseEntity<AuthToken> login(@RequestBody AuthRequest authRequest) {
    return ResponseEntity.ok(authenticationService.login(authRequest));
  }

}
