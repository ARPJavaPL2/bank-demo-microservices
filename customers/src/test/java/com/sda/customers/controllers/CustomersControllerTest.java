package com.sda.customers.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.commons.dto.CustomerDto;
import com.sda.commons.exceptions.ResourceNotFoundException;
import com.sda.customers.services.CustomersService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@WebMvcTest(controllers = CustomersController.class)
@RequiredArgsConstructor
class CustomersControllerTest {

    private static final String API_V_1_CUSTOMERS = "/api/v1/customers/";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CustomersService customersService;

    @Test
    void testGetById() throws Exception {
        //given
        String customerId = "1";

        CustomerDto customerDto = CustomerDto.builder()
                .id(1L)
                .name("Janek")
                .surname("Smith")
                .pesel("90083004219")
                .build();

        String customerDtoAsJson = objectMapper.writeValueAsString(customerDto);

        Mockito.when(customersService.getById(Mockito.anyLong())).thenReturn(customerDto);

        var request = MockMvcRequestBuilders
                .get(API_V_1_CUSTOMERS + customerId);

        //when/then
        mockMvc.perform(request.contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string(customerDtoAsJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Mockito.verify(customersService).getById(customerDto.getId());
    }

    @Test
    void testGetByIdNotFound() throws Exception {
        //given
        String customerId = "1";
        Mockito.when(customersService.getById(Mockito.anyLong()))
                .thenThrow(new ResourceNotFoundException("Customer not found!"));

        var request = MockMvcRequestBuilders
                .get(API_V_1_CUSTOMERS + customerId);

        //when/then
        mockMvc.perform(request.contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();

        Mockito.verify(customersService).getById(Mockito.anyLong());
    }
}