package com.ultranine.spotitunes.service;

import com.ultranine.spotitunes.entities.Account;
import com.ultranine.spotitunes.entities.Song;
import com.ultranine.spotitunes.io.AccountData;
import com.ultranine.spotitunes.io.TransientAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service
public class DatabaseAccessor {
    @Autowired private AccountRepository accountRepository;
    @Autowired private SongRepository songRepository;
    @Autowired private JdbcTemplate jdbcTemplate;

    public Optional<Song> findById(long id) {
        return songRepository.findById(id);
    }

    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    public AccountData registerAccount(TransientAccount account) {
        try {
            String username = account.username;
            if (username.isEmpty() || username.length() > 32) {
                return null;
            }
            username = username.trim().replaceAll("[^a-zA-Z0-9]", "");
            String passHash = account.hashPassword();

            String statement = "CALL add_account(?, ?);";
            jdbcTemplate.update(statement, username, passHash);
            return null;
        }
        catch (DataAccessException | NoSuchAlgorithmException e) {
            return null;
        }
    }

    public AccountData attemptLogin(TransientAccount account) {
        try {
            String username = account.username;
            if (username.isEmpty() || username.length() > 32) {
                return null;
            }
            username = username.trim().replaceAll("[^a-zA-Z0-9]", "");
            String passHash = account.hashPassword();

            List<Account> matches = accountRepository.findByUserName(username);
            for (Account ac : matches) {
                if (ac.passwordHash.equals(passHash)) {
                    return new AccountData(ac.userName, ac.id);
                }
            }
            return null;
        }
        catch (DataAccessException | NoSuchAlgorithmException e) {
            return null;
        }
    }
}
