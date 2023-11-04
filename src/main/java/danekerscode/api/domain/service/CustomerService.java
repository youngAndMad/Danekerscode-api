package danekerscode.api.domain.service;

import danekerscode.api.domain.dto.CustomerDTO;
import danekerscode.api.domain.model.Customer;

import java.util.List;

/**
 * This interface provides methods for managing customers.
 */
public interface CustomerService {
    /**
     * Deletes a customer with the specified ID.
     *
     * @param id the ID of the customer to delete
     */
    void delete(Long id);

    /**
     * Creates a new customer with the given customer DTO.
     *
     * @param customerDTO the customer DTO containing the customer details
     * @return the created customer
     */
    Customer create(CustomerDTO customerDTO);

    /**
     * Retrieves a list of all customers.
     *
     * @return a list of all customers
     */
    List<Customer> findAll();

    /**
     * Updates the customer with the specified ID using the details from the customer DTO.
     *
     * @param customerDTO the customer DTO containing the updated customer details
     * @param id          the ID of the customer to update
     * @return the updated customer
     */
    Customer update(CustomerDTO customerDTO, Long id);

    /**
     * Retrieves the customer with the specified ID.
     *
     * @param id the ID of the customer to retrieve
     * @return the customer with the specified ID
     */
    Customer findById(Long id);
}