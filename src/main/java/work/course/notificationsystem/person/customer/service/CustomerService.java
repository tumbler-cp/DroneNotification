package work.course.notificationsystem.person.customer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import work.course.notificationsystem.person.customer.model.Customer;
import work.course.notificationsystem.person.customer.repository.CustomerRepository;

@Service
@RequiredArgsConstructor
public class CustomerService {

  private final CustomerRepository customerRepository;

  public Customer findById(Long id) {
    return customerRepository.findById(id).orElseThrow(RuntimeException::new);
  }

}
