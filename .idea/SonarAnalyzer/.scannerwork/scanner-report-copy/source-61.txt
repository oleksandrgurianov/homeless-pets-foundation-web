package fontys.sem3.hpfapi.business.impl;

import fontys.sem3.hpfapi.dto.CustomerDTO;
import fontys.sem3.hpfapi.repository.entity.Customer;

final class CustomerDTOConverter {
    private CustomerDTOConverter() { }

    public static CustomerDTO convertToDTO(Customer customer) {
        return CustomerDTO.builder()
                .id(customer.getId())
                .user(UserDTOConverter.convertToDTO(customer.getUser()))
                .street(customer.getStreet())
                .postcode(customer.getPostcode())
                .city(customer.getCity())
                .cardNumber(customer.getCardNumber())
                .expirationDate(customer.getExpirationDate())
                .cvv(customer.getCvv())
                .status(customer.getStatus())
                .build();
    }
}
