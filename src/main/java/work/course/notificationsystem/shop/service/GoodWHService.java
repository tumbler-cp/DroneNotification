package work.course.notificationsystem.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import work.course.notificationsystem.person.sender.enums.Verification;
import work.course.notificationsystem.security.repository.UserRepository;
import work.course.notificationsystem.shop.model.WareHousePosition;
import work.course.notificationsystem.shop.repository.GoodRepository;
import work.course.notificationsystem.shop.repository.WareHousePositionRepository;

@Service
@RequiredArgsConstructor
public class GoodWHService {
  private final GoodRepository goodRepository;
  private final WareHousePositionRepository wareHousePositionRepository;
  private final UserRepository userRepository;

  public void restoreGood(Long goodId, int quantity) {
    var good = goodRepository.findById(goodId)
            .orElseThrow(RuntimeException::new);
    var sender = userRepository.findUByUsername(
                    SecurityContextHolder.getContext()
                            .getAuthentication()
                            .getName()
            )
            .getSender();

    if (sender.getVerification() == Verification.UNVERIFIED) {
      throw new RuntimeException();
    }

    if (sender.getWareHouse() == null) {
      throw new RuntimeException();
    }

    WareHousePosition wh;
    if (wareHousePositionRepository.findByGood(good) != null) {
      wh = wareHousePositionRepository.findByGood(good);
      wh.setQuantity(wh.getQuantity() + quantity);
    }

    wh = WareHousePosition.builder()
            .good(good)
            .quantity(quantity)
            .wareHouse(sender.getWareHouse())
            .build();

    wareHousePositionRepository.save(wh);
  }

  public void removeGood(Long goodId, int quantity) {
    restoreGood(goodId, -quantity);
  }

  public void removePosition(Long goodId) {
    var good = goodRepository.findById(goodId)
            .orElseThrow(RuntimeException::new);
    var wh = wareHousePositionRepository.findByGood(good);
    wareHousePositionRepository.delete(wh);
  }

  public void reserveGood(Long goodId, int quantity) {
    var whp = wareHousePositionRepository.findByGood(
            goodRepository.findById(goodId)
                    .orElseThrow(RuntimeException::new)
    );
    whp.setQuantity(whp.getQuantity() - quantity);
    if (whp.getQuantity() < 0) {
      throw new RuntimeException();
    }
    wareHousePositionRepository.save(whp);
  }

  public void cancelReserve(Long goodId, int quantity) {
    var whp = wareHousePositionRepository.findByGood(
            goodRepository.findById(goodId)
                    .orElseThrow(RuntimeException::new)
    );
    whp.setQuantity(whp.getQuantity() + quantity);
    wareHousePositionRepository.save(whp);
  }
}
