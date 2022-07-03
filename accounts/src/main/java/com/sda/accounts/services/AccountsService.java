package com.sda.accounts.services;


import com.sda.commons.dto.AccountDto;
import com.sda.commons.dto.CustomerDto;
import com.sda.accounts.httpclients.CustomersClient;
import com.sda.commons.mappers.AccountsMapper;
import com.sda.commons.mappers.CustomersMapper;
import com.sda.commons.models.Account;
import com.sda.commons.models.Customer;
import com.sda.accounts.repositories.AccountsRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class AccountsService {

    private final AccountsMapper accountsMapper;
    private final CustomersMapper customersMapper;
    private final CustomersClient customersClient;
    private final AccountsRepository accountsRepository;

    public void create(AccountDto accountDto) {
        log.info("Creating accounts {}", accountDto);
        Account account = accountsMapper.map(accountDto);
        CustomerDto customerDto = customersClient.getById(accountDto.getCustomerId());
        Customer customer = customersMapper.map(customerDto);
        account.setCustomer(customer);

        accountsRepository.save(account);
        log.debug("Account created successfully.");
    }

    public List<AccountDto> getAccountsByCustomerId(Long id) {
        log.info("Fetching accounts by customer id '{}'", id);
        List<Account> accountByCustomerId = accountsRepository.findAccountByCustomerId(id);
        return accountByCustomerId.stream()
                .map(account -> accountsMapper.map(account))
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        log.info("Removing account with id '{}'", id);
        accountsRepository.deleteById(id);
    }
}
