package danekerscode.api.service;

import danekerscode.api.dto.CustomerDTO;
import danekerscode.api.model.Customer;

import java.util.List;

public interface CustomerService {
    void delete(Long id);

    Customer create(CustomerDTO customerDTO);

    List<Customer> findAll();

    Customer update(CustomerDTO customerDTO,Long id);

    Customer findById(Long id);
}
