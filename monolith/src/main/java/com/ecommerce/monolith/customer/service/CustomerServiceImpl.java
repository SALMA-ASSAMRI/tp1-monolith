package com.ecommerce.monolith.customer.service;
import com.ecommerce.monolith.customer.dto.CreateCustomerRequest;
import com.ecommerce.monolith.customer.dto.CustomerDTO;
import com.ecommerce.monolith.customer.entity.Customer;
import com.ecommerce.monolith.customer.mapper.CustomerMapper;
import com.ecommerce.monolith.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return mapper.toDTOList(repository.findAll());
    }
    @Override
    public CustomerDTO getCustomerById(Long id) {
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found: " + id));
        return mapper.toDTO(customer);
    }
    @Override
    public CustomerDTO createCustomer(CreateCustomerRequest request) {
        return mapper.toDTO(repository.save(mapper.toEntity(request)));
    }
    @Override
    public void deleteCustomer(Long id) {
        if (!repository.existsById(id))
            throw new RuntimeException("Customer not found: " + id);
        repository.deleteById(id);
    }
    @Override
    public boolean customerExists(Long id) {
        return repository.existsById(id);
    }
}