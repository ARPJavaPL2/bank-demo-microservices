package com.sda.accounts.httpclients;

import com.sda.commons.dto.CustomerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customers-service", url = "http://localhost:8081/api/v1/customers/")
public interface CustomersClient {

    @GetMapping("{customerId}")
    CustomerDto getById(@PathVariable Long customerId);
}
