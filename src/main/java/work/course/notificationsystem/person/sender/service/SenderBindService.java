package work.course.notificationsystem.person.sender.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import work.course.notificationsystem.person.customer.model.Customer;
import work.course.notificationsystem.person.customer.repository.CustomerRepository;
import work.course.notificationsystem.person.customer.service.CustomerService;
import work.course.notificationsystem.person.sender.dto.SenderTokenDTO;
import work.course.notificationsystem.person.sender.model.Sender;

@Service
@RequiredArgsConstructor
public class SenderBindService {

  private final CustomerRepository customerRepository;
  private final CustomerService customerService;
  private final SenderService senderService;
  private final SenderTokenService senderTokenService;

  public Customer bindCustomerToSender(Long customerId, String token) {
    Customer customer = customerService.findById(customerId);
    Long tokenSenderId = senderTokenService.extractSenderId(token);

    Sender sender = senderService.findById(tokenSenderId);

    customer.getFollowing()
            .add(sender);
    customerRepository.save(customer);
    return customer;
  }

  public Customer unbindCustomerFromSender(Long customerId, String token) {
    Customer customer = customerService.findById(customerId);
    Long tokenSenderId = senderTokenService.extractSenderId(token);

    Sender sender = senderService.findById(tokenSenderId);

    customer.getFollowing()
            .remove(sender);
    customerRepository.save(customer);
    return customer;
  }

  public SenderTokenDTO generateToken(Long senderId) {
    Sender sender = senderService.findById(senderId);
    return SenderTokenDTO.builder()
            .token(
                    senderTokenService.generateToken(sender)
            )
            .build();
  }
}
