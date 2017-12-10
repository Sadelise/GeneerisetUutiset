package com.geneerisetuutiset.repositories;

import com.geneerisetuutiset.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    public Account findByUsername(String username);
}
