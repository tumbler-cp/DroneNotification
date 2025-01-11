package work.course.notificationsystem.person.sender.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import work.course.notificationsystem.person.sender.model.WareHouse;

@Repository
public interface WareHouseRepository extends JpaRepository<WareHouse, Long> {

}
