package work.course.notificationsystem.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import work.course.notificationsystem.shop.model.WareHouse;
import work.course.notificationsystem.shop.repository.WareHouseRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WareHouseService {
  private final WareHouseRepository wareHouseRepository;

  public List<WareHouse> findAll() {
    return wareHouseRepository.findAll();
  }

  public WareHouse findById(Long id) {
    return wareHouseRepository.findById(id).orElse(null);
  }

  public WareHouse save(WareHouse wareHouse) {
    return wareHouseRepository.save(wareHouse);
  }

  public void deleteById(Long id) {
    wareHouseRepository.deleteById(id);
  }

}
