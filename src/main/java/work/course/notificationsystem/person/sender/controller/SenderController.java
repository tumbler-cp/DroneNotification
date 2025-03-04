package work.course.notificationsystem.person.sender.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work.course.notificationsystem.person.sender.dto.SenderDTO;
import work.course.notificationsystem.person.sender.service.SenderService;

@RestController
@RequestMapping("/sender")
@RequiredArgsConstructor
public class SenderController {

  private final SenderService senderService;

  @GetMapping("/{senderId}")
  public ResponseEntity<SenderDTO> getSender(@PathVariable Long senderId) {
    var sender = senderService.findById(senderId);
    return ResponseEntity.ok(senderService.toDTO(sender));
  }
}
