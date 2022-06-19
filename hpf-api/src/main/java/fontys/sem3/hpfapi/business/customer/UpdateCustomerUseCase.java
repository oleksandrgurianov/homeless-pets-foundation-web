package fontys.sem3.hpfapi.business.customer;

import fontys.sem3.hpfapi.dto.customer.UpdateCustomerAddressRequestDTO;
import fontys.sem3.hpfapi.dto.customer.UpdateCustomerBankDetailsRequestDTO;

public interface UpdateCustomerUseCase {
    void updateCustomerAddress(UpdateCustomerAddressRequestDTO request);

    void updateCustomerBankDetails(UpdateCustomerBankDetailsRequestDTO request);
}
