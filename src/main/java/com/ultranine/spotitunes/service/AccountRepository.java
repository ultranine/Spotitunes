package com.ultranine.spotitunes.service;

import com.ultranine.spotitunes.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, String> {
    List<Account> findByUserName(String userName);
}
