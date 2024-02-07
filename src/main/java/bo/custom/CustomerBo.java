package bo.custom;

import dto.CustomerDto;
import entity.Customer;

public interface CustomerBo {
    CustomerDto getCustomerByContactNumber(String contact);
}
