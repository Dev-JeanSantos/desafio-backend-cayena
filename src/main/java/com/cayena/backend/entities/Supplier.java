package com.cayena.backend.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long suplierId;
    private String name;
    private LocalDateTime dateOfCreation;
    private LocalDateTime dateOfTheLastUpdate;
}
