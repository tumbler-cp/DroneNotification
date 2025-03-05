package work.course.notificationsystem.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import work.course.notificationsystem.security.service.UserService;
import work.course.notificationsystem.shop.dto.WareHousePositionDTO;
import work.course.notificationsystem.shop.repository.WareHousePositionRepository;
import work.course.notificationsystem.shop.service.GoodService;
import work.course.notificationsystem.shop.service.GoodWHService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/warehouse")
@RequiredArgsConstructor
public class WareHouseController {
  private final UserService userService;
  private final WareHousePositionRepository wareHousePositionRepository;
  private final GoodService goodService;
  private final GoodWHService goodWHService;

  @GetMapping("/positions")
  public ResponseEntity<List<WareHousePositionDTO>> getWareHousePositions() {
    var user = userService.getCurrentUser();
    if (user.getSender()
            .getWareHouse() == null)
      return ResponseEntity.noContent()
              .build();
    var positions = wareHousePositionRepository.findAllByWareHouse(user.getSender()
            .getWareHouse());
    List<WareHousePositionDTO> ret = new ArrayList<>();
    for (var position : positions) {
      ret.add(
              WareHousePositionDTO.builder()
                      .good(
                              goodService.toDTO(position.getGood())
                      )
                      .quantity(position.getQuantity())
                      .build()
      );
    }
    return ResponseEntity.ok(ret);
  }

  @PostMapping("/good-restore")
  public ResponseEntity<?> restoreGood(@RequestBody WareHousePositionDTO good) {
    goodWHService.restoreGood(good.getGood()
            .getId(), good.getQuantity());
    return ResponseEntity.ok()
            .build();
  }

  public ResponseEntity<?> removeGood(@RequestBody WareHousePositionDTO good) {
    goodWHService.removeGood(good.getGood()
            .getId(), good.getQuantity());
    return ResponseEntity.ok()
            .build();
  }
}
