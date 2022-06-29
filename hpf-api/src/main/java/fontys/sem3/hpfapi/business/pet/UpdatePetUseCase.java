package fontys.sem3.hpfapi.business.pet;

import fontys.sem3.hpfapi.dto.pet.UpdatePetCustomerRequestDTO;

public interface UpdatePetUseCase {
    void updatePetCustomer(UpdatePetCustomerRequestDTO request);
}
