package danekerscode.api.service.impl;

import danekerscode.api.dto.CustomerDTO;
import danekerscode.api.exception.EntityNotFoundException;
import danekerscode.api.mapper.CustomerMapper;
import danekerscode.api.model.Customer;
import danekerscode.api.repository.CustomerRepository;
import danekerscode.api.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public void delete(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Customer create(CustomerDTO customerDTO) {
        var model = customerMapper.toModel(customerDTO);
        return customerRepository.save(model);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll(Sort.by("id"));
    }

    @Override
    public Customer update(CustomerDTO customerDTO, Long id) {
        var customer = this.findById(id);
        customerMapper.update(customer, customerDTO);
        return customerRepository.save(customer);
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Customer.class, id));
    }

}
