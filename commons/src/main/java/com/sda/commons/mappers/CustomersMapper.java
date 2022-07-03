package com.sda.commons.mappers;


import com.sda.commons.dto.CustomerDto;
import com.sda.commons.models.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomersMapper {

    CustomerDto map(Customer customer);

    Customer map(CustomerDto customerDto);
}
