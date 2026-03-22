package com.ultranine.spotitunes.entities;

import jakarta.persistence.*;

@Entity
public class Song {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long songID;

    @Column(length = 64)
    public String songName;

    @Column(length = 32)
    public String songHandle;
}
