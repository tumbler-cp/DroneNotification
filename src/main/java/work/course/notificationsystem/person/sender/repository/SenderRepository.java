package work.course.notificationsystem.person.sender.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import work.course.notificationsystem.person.sender.model.Sender;

@Repository
public interface SenderRepository extends JpaRepository<Sender, Long> {

}
