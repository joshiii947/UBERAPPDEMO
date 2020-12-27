package com.uber.uberapi.repositories;

import com.uber.uberapi.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {


}
