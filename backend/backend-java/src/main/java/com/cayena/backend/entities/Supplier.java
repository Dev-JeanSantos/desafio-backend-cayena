package com.cayena.backend.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("suplier_id")
    private Long suplierId;
    private String name;
    @JsonProperty("date_of_creation")
    private LocalDateTime dateOfCreation;
    @JsonProperty("date_of_the_last_update")
    private LocalDateTime dateOfTheLastUpdate;
}
