package com.sda.customers.repositories;

import com.sda.commons.models.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@SqlGroup({
        @Sql(scripts = "classpath:data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(scripts = "classpath:clean-up.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
class CustomersRepositoryTest {

    @Autowired
    private CustomersRepository customersRepository;

    @Test
    void testSaveNewCustomerSuccess() {
        //given
        Customer expectedCustomer = Customer.builder()
                .name("Janek")
                .surname("Smith")
                .pesel("90083004219")
                .build();

        //when
        Customer actualCustomer = customersRepository.save(expectedCustomer);

        //then
        assertNotNull(actualCustomer.getId());
        assertEquals(expectedCustomer, actualCustomer);
        assertEquals(expectedCustomer.getName(), actualCustomer.getName());
        assertEquals(expectedCustomer.getSurname(), actualCustomer.getSurname());
        assertEquals(expectedCustomer.getPesel(), actualCustomer.getPesel());
    }

    @Test
    void testGetCustomersListSuccess() {
        //given
        Customer customer = Customer.builder()
                .name("Janek")
                .surname("Smith")
                .pesel("90083004219")
                .build();

        Customer customer2 = Customer.builder()
                .id(3L)
                .name("Mark")
                .surname("Doe")
                .pesel("90083078213")
                .build();

        Customer expectedCustomer = customersRepository.save(customer);

        //when
        List<Customer> result = customersRepository.findAll();

        //then
        assertNotNull(result);
        assertEquals(2, result.size());

        Customer actualCustomer = result.get(0);

        assertEquals(expectedCustomer, actualCustomer);
        assertEquals(expectedCustomer.getName(), actualCustomer.getName());
        assertEquals(expectedCustomer.getSurname(), actualCustomer.getSurname());
        assertEquals(expectedCustomer.getPesel(), actualCustomer.getPesel());

        actualCustomer = result.get(1);

        assertEquals(customer2, actualCustomer);
        assertEquals(customer2.getName(), actualCustomer.getName());
        assertEquals(customer2.getSurname(), actualCustomer.getSurname());
        assertEquals(customer2.getPesel(), actualCustomer.getPesel());
    }
}