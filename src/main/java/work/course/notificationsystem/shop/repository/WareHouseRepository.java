package work.course.notificationsystem.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import work.course.notificationsystem.shop.model.WareHouse;

@Repository
public interface WareHouseRepository extends JpaRepository<WareHouse, Long> {

}
