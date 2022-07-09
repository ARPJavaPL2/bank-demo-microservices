package com.sda.customers.services;


import com.sda.commons.dto.CustomerDto;
import com.sda.commons.exceptions.ResourceNotFoundException;
import com.sda.commons.exceptions.ResourceValidationException;
import com.sda.commons.mappers.CustomersMapper;
import com.sda.commons.models.Customer;
import com.sda.customers.repositories.CustomersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomersService {

    private final CustomersMapper customersMapper;
    private final CustomersRepository customersRepository;

    public void create(CustomerDto customerDto) {
        log.info("Creating customer {}", customerDto);
        Customer customer = customersMapper.map(customerDto);
        customersRepository.save(customer);
        log.debug("Customer created successfully.");
    }

    public CustomerDto getById(Long id) {
        log.info("Fetching customer with id '{}'", id);
        Customer customer = getCustomer(id);
        return customersMapper.map(customer);
    }

    public List<CustomerDto> getPage() {
        log.info("Fetching customers page");
        return customersRepository.findAll().stream()
                .map(customer -> customersMapper.map(customer))
                .collect(Collectors.toList());
    }

    public void updateById(Long id, CustomerDto customerDto) {
        log.info("Updating customer with id '{}'. New customer: {}", id, customerDto);
        if (!customersRepository.existsById(id)) {
            log.warn("Customer with id '{}' not found", id);
            throw getCustomerNotFoundException(id);
        }

        Long customerDtoId = customerDto.getId();
        if (!id.equals(customerDtoId)) {
            String message = String.format("Id parameter '%s' and customer id '%s' does not match", id, customerDtoId);
            log.warn(message);
            throw new ResourceValidationException(message);
        }

        Customer customer = customersMapper.map(customerDto);
        customersRepository.save(customer);
    }

    public void deleteById(Long id) {
        log.info("Removing customer with id '{}'", id);
        customersRepository.deleteById(id);
    }

    public Customer getCustomer(Long id) {
        return customersRepository.findById(id)
                .orElseThrow(() -> getCustomerNotFoundException(id));
    }

    private ResourceNotFoundException getCustomerNotFoundException(Long id) {
        return new ResourceNotFoundException(String.format("Customer with id '%s' not found", id));
    }
}
