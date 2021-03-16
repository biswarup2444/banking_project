package com.bankingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankingservice.model.Account;

@Repository

public interface AccountRepository extends JpaRepository<Account, String> {

}
