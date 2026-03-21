package com.ultranine.spotitunes.service;

import com.ultranine.spotitunes.entities.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DatabaseAccessor {
    @Autowired private TestRepository testRepository;
    @Autowired private JdbcTemplate jdbcTemplate;

    public Optional<Test> findById(long id) {
        return testRepository.findById(id);
    }
}
