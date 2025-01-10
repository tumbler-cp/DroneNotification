package work.course.notificationsystem.security.repository;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import work.course.notificationsystem.security.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  UserDetails findByEmail(@NotNull @NotBlank String email);

  UserDetails findByUsername(@NotNull @NotBlank @Size(min = 4, max = 120) String username);

}
