package work.course.notificationsystem.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import work.course.notificationsystem.shop.dto.GoodDTO;
import work.course.notificationsystem.shop.dto.NewGoodDTO;
import work.course.notificationsystem.shop.service.GoodService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/good")
public class GoodController {

  private final GoodService goodService;

  @PostMapping("/new")
  public GoodDTO newGood(NewGoodDTO newGoodDTO) {
    return goodService.save(newGoodDTO);
  }

  @PostMapping("/update")
  public GoodDTO updateGood(GoodDTO goodDTO) {
    return goodService.update(goodDTO);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> deleteGood(@PathVariable Long id) {
    goodService.delete(id);
    return ResponseEntity.ok()
            .build();
  }
}
