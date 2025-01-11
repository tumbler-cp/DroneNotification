package work.course.notificationsystem.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import work.course.notificationsystem.order.model.Order;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
  List<Order> findAllByCustomerId(Long userId);
  List<Order> findAllBySenderId(Long userId);
}
