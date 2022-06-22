//package fontys.sem3.hpfapi.business.customer.impl;
//
//import fontys.sem3.hpfapi.business.customer.CreateCustomerUseCase;
//import fontys.sem3.hpfapi.business.validator.UserIdValidator;
//import fontys.sem3.hpfapi.dto.customer.CreateCustomerRequestDTO;
//import fontys.sem3.hpfapi.dto.customer.CreateCustomerResponseDTO;
//import fontys.sem3.hpfapi.repository.CustomerRepository;
//import fontys.sem3.hpfapi.repository.entity.Customer;
//import fontys.sem3.hpfapi.repository.entity.User;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//
//@Service
//@RequiredArgsConstructor
//public class CreateCustomerUseCaseImpl implements CreateCustomerUseCase {
//    private final CustomerRepository customerRepository;
//    private final UserIdValidator userIdValidator;
//
//    @Transactional
//    @Override
//    public CreateCustomerResponseDTO createCustomer(CreateCustomerRequestDTO request) {
//        userIdValidator.validateId(request.getUserId());
//
//        Customer newCustomer = Customer.builder()
//                .user(User.builder().id(request.getUserId()).build())
//                .street(request.getStreet())
//                .postcode(request.getPostcode())
//                .city(request.getCity())
//                .cardNumber(request.getCardNumber())
//                .expirationDate(request.getExpirationDate())
//                .cvv(request.getCvv())
//                .build();
//
//        Customer savedCustomer = customerRepository.save(newCustomer);
//
//        return CreateCustomerResponseDTO.builder()
//                .customerId(savedCustomer.getId())
//                .build();
//    }
//}
