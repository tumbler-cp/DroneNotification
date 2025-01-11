package work.course.notificationsystem.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import work.course.notificationsystem.order.model.OrderPosition;

import java.util.List;

@Repository
public interface OrderPositionRepository extends JpaRepository<OrderPosition, Long> {
  List<OrderPosition> findAllByOrderId(Long orderId);
}
