package work.course.notificationsystem.person.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import work.course.notificationsystem.person.customer.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
