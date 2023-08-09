package com.cayena.backend.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String name;
    private Integer quantityInStock;
    private BigDecimal unitPrice;
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Suplier suplierId;
    private LocalDate dateOfCreation;
    private LocalDate dateOfTheLastUpdate;
}
