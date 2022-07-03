package com.sda.commons.mappers;

import com.sda.commons.dto.CurrencyDto;
import com.sda.commons.models.Currency;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CurrenciesMapper {

    CurrencyDto map(Currency currency);

    Currency map(CurrencyDto currencyDto);
}
