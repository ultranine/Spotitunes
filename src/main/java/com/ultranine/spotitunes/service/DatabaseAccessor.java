package com.ultranine.spotitunes.service;

import com.ultranine.spotitunes.entities.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DatabaseAccessor {
    @Autowired private SongRepository songRepository;
    @Autowired private JdbcTemplate jdbcTemplate;

    public Optional<Song> findById(long id) {
        return songRepository.findById(id);
    }

    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }
}
