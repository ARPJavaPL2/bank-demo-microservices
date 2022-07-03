package com.sda.commons.mappers;

import com.sda.commons.dto.CardDto;
import com.sda.commons.models.Card;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CardsMapper {

    CardDto map(Card card);

    Card map(CardDto cardDto);
}
