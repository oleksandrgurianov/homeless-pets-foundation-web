package fontys.sem3.hpfapi.business;

import fontys.sem3.hpfapi.dto.UpdatePetCustomerRequestDTO;
import fontys.sem3.hpfapi.dto.UpdatePetDetailsRequestDTO;

public interface UpdatePetUseCase {
    void updatePetCustomer(UpdatePetCustomerRequestDTO request);

    void updatePetDetails(UpdatePetDetailsRequestDTO request);
}
