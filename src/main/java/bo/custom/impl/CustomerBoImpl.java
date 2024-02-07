package bo.custom.impl;

import bo.custom.CustomerBo;
import dao.custom.CustomerDao;
import dao.custom.impl.CustomerDaoImpl;
import dto.CustomerDto;
import entity.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerBoImpl implements CustomerBo {
    private CustomerDao customerDao = new CustomerDaoImpl();

    @Override
    public CustomerDto getCustomerByContactNumber(String contact) {
        Customer customer = customerDao.getByContactNumber(contact);
        return customer == null ? null :
                new CustomerDto(
                        customer.getId(),
                        customer.getName(),
                        customer.getContactNumber(),
                        customer.getEmail()
                );
    }
}
