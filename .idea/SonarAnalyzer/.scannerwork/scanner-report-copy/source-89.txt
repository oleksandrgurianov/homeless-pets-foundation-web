package fontys.sem3.hpfapi.business;

import fontys.sem3.hpfapi.dto.GetCustomersRequestDTO;
import fontys.sem3.hpfapi.dto.GetCustomersResponseDTO;

public interface GetCustomersUseCase {
    GetCustomersResponseDTO getCustomers(GetCustomersRequestDTO request);
}
