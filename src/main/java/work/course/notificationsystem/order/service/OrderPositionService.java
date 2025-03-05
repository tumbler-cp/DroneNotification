package work.course.notificationsystem.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import work.course.notificationsystem.order.dto.OrderPositionDTO;
import work.course.notificationsystem.order.model.Order;
import work.course.notificationsystem.order.model.OrderPosition;
import work.course.notificationsystem.order.repository.OrderPositionRepository;
import work.course.notificationsystem.shop.model.Good;
import work.course.notificationsystem.shop.service.GoodService;

@Service
@RequiredArgsConstructor
public class OrderPositionService {
  private final OrderPositionRepository orderPositionRepository;
  private final GoodService goodService;

  public OrderPositionDTO toDTO(OrderPosition orderPosition) {
    return OrderPositionDTO.builder()
            .good(goodService.toDTO(orderPosition.getGood()))
            .quantity(orderPosition.getQuantity())
            .build();
  }

  public void addPosition(Order order, Good good, int quantity) {
    OrderPosition position = OrderPosition.builder()
            .order(order)
            .good(good)
            .quantity(quantity)
            .build();
    orderPositionRepository.save(position);
  }

  public void removePosition(Order order, Good good) {
    var position = orderPositionRepository.findByOrderAndGood(order, good);
    orderPositionRepository.delete(position);
  }

}
