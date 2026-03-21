package com.ultranine.spotitunes.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Test {
    @Id
    public long testId;

    @Column(length = 16)
    public String testString;
}
