package com.sda.commons.mappers;


import com.sda.commons.dto.AccountDto;
import com.sda.commons.models.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountsMapper {

    @Mapping(target = "customerId", source = "customer.id")
    AccountDto map(Account account);

    @Mapping(target = "customer", ignore = true)
    Account map(AccountDto accountDto);
}
