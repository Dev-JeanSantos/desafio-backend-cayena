package com.cayena.backend.repositories;

import com.cayena.backend.entities.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.NoSuchElementException;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository repository;

    private long existingId;
    private long notExistingId;

    @BeforeEach
    void setup() throws Exception {
        existingId = 1L;
        notExistingId = 1000L;
    }

    @Test
    public void shouldDeleteAnObjectWhenIsExists() {

        Optional<Product> obj = repository.findById(existingId);
        Product product = obj.get();
        repository.delete(product);
        Optional<Product> obj_delete = repository.findById(existingId);

        Assertions.assertFalse(obj_delete.isPresent());

    }

    @Test
    public void shouldThrowAnExceptionWhenDeletingAnObjectWithAnIdThatDoesNotExist() {

        Assertions.assertThrows(NoSuchElementException.class, () -> {
            Optional<Product> obj = repository.findById(notExistingId);
            Product product = obj.get();
            repository.delete(product);
        });
    }

}
