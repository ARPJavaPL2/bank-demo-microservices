package com.sda.accounts.controllers;


import com.sda.accounts.services.AccountsService;
import com.sda.commons.dto.AccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
public class AccountsController {

    private final AccountsService accountsService;

    @GetMapping("customer/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountDto> getByCustomerId(@PathVariable Long id) {
        return accountsService.getAccountsByCustomerId(id);
    }

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody AccountDto accountDto) {
        accountsService.create(accountDto);
    }

    @DeleteMapping("delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        accountsService.deleteById(id);
    }

}


