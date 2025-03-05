package work.course.notificationsystem.order.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import work.course.notificationsystem.order.dto.OrderDTO;
import work.course.notificationsystem.order.service.OrderService;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;

  @PostMapping("/new")
  public ResponseEntity<?> newOrder(@RequestBody OrderDTO orderDTO) {
    orderService.createOrder(orderDTO);
    return ResponseEntity.ok()
            .build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<OrderDTO> getOrder(@PathVariable Long id) {
    return ResponseEntity.ok(
            orderService.toDTO(
                    orderService.getOrderById(id))
    );
  }

  @PostMapping("/{id}/{acceptance}")
  public ResponseEntity<OrderDTO> acceptOrder(@PathVariable Long id, @PathVariable String acceptance) {
    return ResponseEntity.ok(
            orderService.toDTO(
                    orderService.acceptOrder(id, acceptance.equals("accept"))
            ));
  }
}
