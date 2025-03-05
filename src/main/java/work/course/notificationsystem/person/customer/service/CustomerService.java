package work.course.notificationsystem.person.customer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import work.course.notificationsystem.person.customer.dto.CustomerDTO;
import work.course.notificationsystem.person.customer.model.Customer;
import work.course.notificationsystem.person.customer.repository.CustomerRepository;

@Service
@RequiredArgsConstructor
public class CustomerService {

  private final CustomerRepository customerRepository;

  public CustomerDTO toDTO(Customer customer) {
    return CustomerDTO.builder()
            .id(customer.getId())
            .address(customer.getAddress())
            .userId(customer.getUser()
                    .getId())
            .build();
  }

  public Customer findById(Long id) {
    return customerRepository.findById(id)
            .orElseThrow(RuntimeException::new);
  }

}
