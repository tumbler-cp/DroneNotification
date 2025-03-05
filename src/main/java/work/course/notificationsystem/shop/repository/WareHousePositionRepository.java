package work.course.notificationsystem.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import work.course.notificationsystem.shop.model.Good;
import work.course.notificationsystem.shop.model.WareHouse;
import work.course.notificationsystem.shop.model.WareHousePosition;

import java.util.List;

@Repository
public interface WareHousePositionRepository extends JpaRepository<WareHousePosition, Long> {

  WareHousePosition findByGood(Good good);

  List<WareHousePosition> findAllByWareHouse(WareHouse wareHouse);
}
