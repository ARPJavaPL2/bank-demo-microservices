package com.sda.commons.dto;

import com.sda.commons.enums.CardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {

    private Long id;
    private String cardNumber;
    private LocalDate expiredDate;
    private String csvCode;
    private CardType cardType;
    private AccountDto account;

}
