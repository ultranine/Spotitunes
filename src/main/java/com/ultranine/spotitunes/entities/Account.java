package com.ultranine.spotitunes.entities;

import jakarta.persistence.*;

@Entity
public class Account {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @Column(length = 32)
    public String userName;

    @Column(length = 64)
    public String passwordHash;
}
