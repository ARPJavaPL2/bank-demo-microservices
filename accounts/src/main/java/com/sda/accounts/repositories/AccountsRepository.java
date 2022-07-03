package com.sda.accounts.repositories;


import com.sda.commons.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountsRepository extends JpaRepository<Account, Long> {

    @Query("SELECT a FROM Account a WHERE a.customer.id = :customerId")
    List<Account> findAccountByCustomerId(Long customerId);
    //    Can be done this way as well:
    //    List<Account> findAccountByCustomerId(Long customerId);
}
