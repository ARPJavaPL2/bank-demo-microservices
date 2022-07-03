package com.sda.customers.controllers;


import com.sda.commons.dto.CustomerDto;
import com.sda.customers.services.CustomersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomersController {

    private final CustomersService customersService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerDto> getPage() {
        return customersService.getPage();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto getById(@PathVariable Long id) {
        return customersService.getById(id);
    }

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CustomerDto customerDto) {
        customersService.create(customerDto);
    }

    @PutMapping("update/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void update(@PathVariable Long id,
                       @RequestBody CustomerDto customerDto) {

        customersService.updateById(id, customerDto);
    }

    @DeleteMapping("delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        customersService.deleteById(id);
    }

}


