package work.course.notificationsystem.security.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import work.course.notificationsystem.person.customer.model.Customer;
import work.course.notificationsystem.person.customer.repository.CustomerRepository;
import work.course.notificationsystem.person.sender.model.Sender;
import work.course.notificationsystem.person.sender.repository.SenderRepository;
import work.course.notificationsystem.security.dto.request.AuthRequest;
import work.course.notificationsystem.security.dto.response.AuthToken;
import work.course.notificationsystem.security.model.User;
import work.course.notificationsystem.security.model.enums.Role;
import work.course.notificationsystem.security.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final CustomerRepository customerRepository;
  private final SenderRepository senderRepository;

  public AuthToken register(AuthRequest authRequest) {
    var user = User
        .builder()
        .username(authRequest.getUsername())
        .password(passwordEncoder.encode(authRequest.getPassword()))
        .role(Role.USER)
        .build();
    user = userRepository.save(user);
    var token = jwtService.generateToken(user);

    var customer = Customer.builder()
        .user(user)
        .build();
    customerRepository.save(customer);

    var sender = Sender.builder()
        .user(user)
        .build();
    senderRepository.save(sender);

    return AuthToken
        .builder()
        .token(token)
        .build();
  }

  public AuthToken login(AuthRequest authRequest) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            authRequest.getUsername(), authRequest.getPassword()
        )
    );
    var user = userRepository.findByUsername(authRequest.getUsername());
    var token = jwtService.generateToken(user);
    return AuthToken
        .builder()
        .token(token)
        .build();
  }

  public UserDetails getCurrentUserDetails() {
    return userRepository.findByUsername(SecurityContextHolder
        .getContext()
        .getAuthentication()
        .getName());
  }

}
