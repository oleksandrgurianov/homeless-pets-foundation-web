package fontys.sem3.hpfapi.business.impl;

import fontys.sem3.hpfapi.business.GetCustomersUseCase;
import fontys.sem3.hpfapi.dto.CustomerDTO;
import fontys.sem3.hpfapi.dto.GetCustomersRequestDTO;
import fontys.sem3.hpfapi.dto.GetCustomersResponseDTO;
import fontys.sem3.hpfapi.repository.CustomerRepository;
import fontys.sem3.hpfapi.repository.entity.Customer;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.List;

@Service
@AllArgsConstructor
public class GetCustomersUseCaseImpl implements GetCustomersUseCase {
    private CustomerRepository customerRepository;

    @Override
    public GetCustomersResponseDTO getCustomers(final GetCustomersRequestDTO request) {
        List<Customer> results;

        if (StringUtils.hasText(request.getUserFullName())) {
            if (BooleanUtils.isNotFalse(request.getAscending())) {
                results = customerRepository.findAllByUserFullNameContainingAndStatusOrderByUserFullNameAsc(request.getUserFullName(), request.getStatus());
            } else {
                results = customerRepository.findAllByUserFullNameContainingAndStatusOrderByUserFullNameDesc(request.getUserFullName(), request.getStatus());
            }
        } else {
            if (BooleanUtils.isNotFalse(request.getAscending())) {
                results = customerRepository.findAllByStatusOrderByUserFullNameAsc(request.getStatus());
            } else {
                results = customerRepository.findAllByStatusOrderByUserFullNameDesc(request.getStatus());
            }
        }

        final GetCustomersResponseDTO response = new GetCustomersResponseDTO();
        List<CustomerDTO> customersDTO = results
                .stream()
                .map(CustomerDTOConverter::convertToDTO)
                .toList();
        response.setCustomers(customersDTO);
        return response;
    }
}
