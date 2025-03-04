package work.course.notificationsystem.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import work.course.notificationsystem.shop.model.Good;
import work.course.notificationsystem.shop.repository.GoodRepository;

@Service
@RequiredArgsConstructor
public class GoodWHService {
  private final GoodRepository goodRepository;

  public void restoreGood(Long goodId, int quantity) {
    var good = goodRepository.findById(goodId)
            .orElseThrow(RuntimeException::new);

  }
}
