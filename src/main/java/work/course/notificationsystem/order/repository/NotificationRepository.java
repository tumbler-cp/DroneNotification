package work.course.notificationsystem.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import work.course.notificationsystem.order.model.Notification;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
  List<Notification> findAllByOrder_Id(Long orderId);
}
