package work.course.notificationsystem.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import work.course.notificationsystem.security.repository.UserRepository;
import work.course.notificationsystem.shop.dto.GoodDTO;
import work.course.notificationsystem.shop.dto.NewGoodDTO;
import work.course.notificationsystem.shop.model.Good;
import work.course.notificationsystem.shop.repository.GoodRepository;

@Service
@RequiredArgsConstructor
public class GoodService {
  private final GoodRepository goodRepository;
  private final UserRepository userRepository;

  public GoodDTO toDTO(Good good) {
    return GoodDTO.builder()
            .id(good.getId())
            .name(good.getName())
            .weight(good.getWeight())
            .description(good.getDescription())
            .senderDemoName(good.getOwner()
                    .getDemoName())
            .build();
  }

  @Transactional
  public GoodDTO save(NewGoodDTO good) {
    var owner = userRepository.findUByUsername(
            SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getName()
    );
    Good newGood = Good.builder()
            .name(good.getName())
            .weight(good.getWeight())
            .description(good.getDescription())
            .build();
    goodRepository.save(newGood);
    return toDTO(newGood);
  }

  public GoodDTO update(GoodDTO goodDTO) {
    var owner = userRepository.findUByUsername(
            SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getName()
    );
    var good = goodRepository.findById(goodDTO.getId())
            .orElseThrow(RuntimeException::new);
    if (good.getOwner() != owner.getSender()) throw new RuntimeException();
    good.setName(goodDTO.getName());
    good.setWeight(goodDTO.getWeight());
    good.setDescription(goodDTO.getDescription());
    goodRepository.save(good);
    return toDTO(good);
  }
}
