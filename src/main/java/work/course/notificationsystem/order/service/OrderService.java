package work.course.notificationsystem.order.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import work.course.notificationsystem.order.dto.OrderDTO;
import work.course.notificationsystem.order.dto.OrderPositionDTO;
import work.course.notificationsystem.order.model.Order;
import work.course.notificationsystem.order.model.OrderStatus;
import work.course.notificationsystem.order.model.enums.OrderStatusStamp;
import work.course.notificationsystem.order.repository.OrderRepository;
import work.course.notificationsystem.order.repository.OrderStatusRepository;
import work.course.notificationsystem.person.customer.repository.CustomerRepository;
import work.course.notificationsystem.person.customer.service.CustomerService;
import work.course.notificationsystem.person.sender.service.SenderService;
import work.course.notificationsystem.shop.repository.GoodRepository;
import work.course.notificationsystem.shop.service.GoodWHService;
import work.course.notificationsystem.shop.service.WareHouseService;
import work.course.notificationsystem.shop.stationapi.client.ApiSimService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderService {
  private final CustomerRepository customerRepository;
  private final SenderService senderService;
  private final OrderPositionService orderCreationService;
  private final GoodRepository goodRepository;
  private final CustomerService customerService;
  private final OrderPositionService orderPositionService;
  private final OrderRepository orderRepository;
  private final OrderStatusRepository orderStatusRepository;
  private final WareHouseService wareHouseService;
  private final GoodWHService goodWHService;
  private final ApiSimService apiSimService;

  public OrderDTO toDTO(Order order) {
    List<OrderPositionDTO> positions = new ArrayList<>();
    for (var p : order.getPositions()) {
      positions.add(orderPositionService.toDTO(p));
    }
    return OrderDTO.builder()
            .id(order.getId())
            .createTime(order.getCreationTime())
            .sender(
                    senderService.toDTO(order.getSender())
            )
            .customer(customerService.toDTO(order.getCustomer()))
            .positions(
                    positions
            )
            .build();
  }

  public Order getOrderById(Long id) {
    return orderRepository.findById(id)
            .orElseThrow(RuntimeException::new);
  }

  public OrderStatus createOrderStatus(OrderDTO orderDTO) {
    String customerAddr = orderDTO.getCustomer()
            .getAddress();
    String senderAddr = orderDTO.getSender()
            .getStationDTO()
            .getAddress();

    var customerC = apiSimService.forwardGeocode(customerAddr);
    var senderC = apiSimService.forwardGeocode(senderAddr);

    return OrderStatus.builder()
            .createTime(new Date())
            .estimatedTime(
                    apiSimService.estimateTime(customerC.get("latitude"), customerC.get("longitude"),
                            senderC.get("latitude"), senderC.get("longitude"), 40)
            )
            .status(OrderStatusStamp.CREATED)
            .build();
  }

  @Transactional
  public Order acceptOrder(Long orderId, boolean accept) {
    var order = orderRepository.findById(orderId)
            .orElseThrow(RuntimeException::new);
    order.getOrderStatus()
            .setStatus(accept ? OrderStatusStamp.APPROVED : OrderStatusStamp.REJECTED);
    if (order.getOrderStatus()
            .getStatus() == OrderStatusStamp.REJECTED) {
      for (var p : order.getPositions()) {
        goodWHService.cancelReserve(p.getGood()
                .getId(), p.getQuantity());
      }
    }
    orderStatusRepository.save(order.getOrderStatus());
    orderRepository.save(order);
    return order;
  }

  @Transactional
  public Order createOrder(OrderDTO orderDTO) {
    Order order = Order.builder()
            .customer(
                    customerRepository.findById(orderDTO.getCustomer()
                                    .getId())
                            .orElseThrow(RuntimeException::new)
            )
            .sender(
                    senderService.findById(orderDTO.getSender()
                            .getId())
            )
            .orderStatus(createOrderStatus(orderDTO))
            .build();
    var positions = orderDTO.getPositions();
    for (var position : positions) {
      orderCreationService.addPosition(order,
              goodRepository.findById(position.getGood()
                              .getId())
                      .orElseThrow(RuntimeException::new),
              position.getQuantity()
      );
      goodWHService.reserveGood(position.getGood()
              .getId(), position.getQuantity());
    }
    orderRepository.save(order);
    orderStatusRepository.save(order.getOrderStatus());
    return order;
  }
}
