package work.course.notificationsystem.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import work.course.notificationsystem.order.model.Order;
import work.course.notificationsystem.order.model.OrderPosition;
import work.course.notificationsystem.shop.model.Good;

import java.util.List;

@Repository
public interface OrderPositionRepository extends JpaRepository<OrderPosition, Long> {
  List<OrderPosition> findAllByOrderId(Long orderId);

  OrderPosition findByOrderAndGood(Order order, Good good);
}
