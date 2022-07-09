package com.sda.customers.services;

import com.sda.commons.dto.CustomerDto;
import com.sda.commons.exceptions.ResourceNotFoundException;
import com.sda.commons.exceptions.ResourceValidationException;
import com.sda.commons.mappers.CustomersMapper;
import com.sda.commons.models.Customer;
import com.sda.customers.repositories.CustomersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;

@SpringBootTest
class CustomersServiceTest {

    @MockBean
    private CustomersRepository customersRepository;

    @SpyBean
    private CustomersMapper customersMapper;

    @Autowired
    private CustomersService customersService;

    private CustomerDto customerDto;

    @BeforeEach
    void reset() {
        Mockito.reset(customersRepository);

        customerDto = CustomerDto.builder()
                .id(1L)
                .name("Janek")
                .surname("Smith")
                .pesel("90083004219")
                .build();

    }

    @Test
    void testCreateSuccess() {
        //given
        Customer customer = customersMapper.map(customerDto);

        //when
        customersService.create(customerDto);

        //then
        Mockito.verify(customersMapper, times(2)).map(customerDto);
        Mockito.verify(customersRepository).save(customer);
        Mockito.verifyNoMoreInteractions(customersMapper, customersRepository);
    }


    @Test
    void testCreateSuccess2() {
        //given/when
        customersService.create(customerDto);

        //then
        Mockito.verify(customersMapper).map(customerDto);
        Mockito.verify(customersRepository).save(Mockito.any(Customer.class));
        Mockito.verifyNoMoreInteractions(customersMapper, customersRepository);
    }


    @Test
    void testUpdateHappyPath() {
        //given
        final Long id = customerDto.getId();
        final Customer expectedCustomer = customersMapper.map(customerDto);

        Mockito.when(customersRepository.existsById(id)).thenReturn(true);

        //when
        customersService.updateById(id, customerDto);

        //then
        Mockito.verify(customersRepository).existsById(id);
        Mockito.verify(customersMapper, times(2)).map(customerDto);
        Mockito.verify(customersRepository).save(expectedCustomer);
        Mockito.verifyNoMoreInteractions(customersMapper, customersRepository);
    }

    @Test
    void testUpdateCustomerNotFound() {
        //given
        final Long id = customerDto.getId();
        String expectedMessage = String.format("Customer with id '%s' not found", id);

        Mockito.when(customersRepository.existsById(id)).thenReturn(false);

        //when
        Executable executable = () -> customersService.updateById(id, customerDto);

        //then
        RuntimeException exception = assertThrows(ResourceNotFoundException.class, executable);
        assertEquals(expectedMessage, exception.getMessage());
        Mockito.verify(customersRepository).existsById(id);
        Mockito.verifyNoInteractions(customersMapper);
        Mockito.verifyNoMoreInteractions(customersRepository);
    }

    @Test
    void testUpdateIdsConflict() {
        //given
        final Long id = 2L;
        Mockito.when(customersRepository.existsById(id)).thenReturn(true);

        //when
        assertThrows(ResourceValidationException.class, () -> customersService.updateById(id, customerDto));

        //then
        Mockito.verify(customersRepository).existsById(id);
        Mockito.verifyNoInteractions(customersMapper);
        Mockito.verifyNoMoreInteractions(customersRepository);
    }

}