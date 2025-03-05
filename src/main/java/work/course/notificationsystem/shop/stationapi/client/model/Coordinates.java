package work.course.notificationsystem.shop.stationapi.client.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Coordinates {
  double latitude;
  double longitude;
}
