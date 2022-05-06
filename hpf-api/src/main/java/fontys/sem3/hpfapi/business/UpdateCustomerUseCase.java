package fontys.sem3.hpfapi.business;

import fontys.sem3.hpfapi.dto.UpdateCustomerAddressRequestDTO;
import fontys.sem3.hpfapi.dto.UpdateCustomerBankDetailsRequestDTO;
import fontys.sem3.hpfapi.dto.UpdateCustomerStatusRequestDTO;

public interface UpdateCustomerUseCase {
    void updateCustomerAddress(UpdateCustomerAddressRequestDTO request);

    void updateCustomerBankDetails(UpdateCustomerBankDetailsRequestDTO request);

    void updateCustomerStatus(UpdateCustomerStatusRequestDTO request);
}
