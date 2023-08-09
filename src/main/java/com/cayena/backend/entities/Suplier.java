package com.cayena.backend.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Suplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long suplierId;
    private String name;
    private LocalDate dateOfCreation;
    private LocalDate dateOfTheLastUpdate;
}
