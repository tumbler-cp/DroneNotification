package work.course.notificationsystem.person.sender.repository;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import work.course.notificationsystem.person.sender.model.Sender;
import work.course.notificationsystem.security.model.User;

@Repository
public interface SenderRepository extends JpaRepository<Sender, Long> {

  Sender findSenderByUser(@NotNull User user);
}
