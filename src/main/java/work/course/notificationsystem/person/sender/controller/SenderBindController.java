package work.course.notificationsystem.person.sender.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import work.course.notificationsystem.person.customer.dto.CustomerDTO;
import work.course.notificationsystem.person.sender.dto.SenderTokenDTO;
import work.course.notificationsystem.person.sender.service.SenderBindService;

@RestController
@RequestMapping("/sender")
@RequiredArgsConstructor
public class SenderBindController {

  private final SenderBindService senderBindService;

  @PostMapping("/bind-customer/{customerId}")
  public ResponseEntity<CustomerDTO> bindCustomerToSender(@PathVariable Long customerId, @RequestBody SenderTokenDTO token) {
    var customer = senderBindService.bindCustomerToSender(customerId, token.getToken());
    return ResponseEntity.ok(CustomerDTO.builder()
            .id(customer.getId())
            .address(customer.getAddress())
            .userId(customer.getUser()
                    .getId())
            .build());
  }

  @PostMapping("/unbind-customer/{customerId}")
  public ResponseEntity<CustomerDTO> unbindCustomerFromSender(@PathVariable Long customerId, @RequestBody SenderTokenDTO token) {
    var customer = senderBindService.unbindCustomerFromSender(customerId, token.getToken());
    return ResponseEntity.ok(CustomerDTO.builder()
            .id(customer.getId())
            .address(customer.getAddress())
            .userId(customer.getUser()
                    .getId())
            .build());
  }

  @PostMapping("/{senderId}/generate-token")
  public ResponseEntity<SenderTokenDTO> generateToken(@PathVariable Long senderId) {
    return ResponseEntity.ok(senderBindService.generateToken(senderId));
  }
}
