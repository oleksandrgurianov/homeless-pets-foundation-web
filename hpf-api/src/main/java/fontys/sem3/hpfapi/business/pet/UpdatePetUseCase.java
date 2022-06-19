package fontys.sem3.hpfapi.business.pet;

import fontys.sem3.hpfapi.dto.pet.UpdatePetCustomerRequestDTO;
import fontys.sem3.hpfapi.dto.pet.UpdatePetDetailsRequestDTO;

public interface UpdatePetUseCase {
    void updatePetCustomer(UpdatePetCustomerRequestDTO request);

    void updatePetDetails(UpdatePetDetailsRequestDTO request);
}
