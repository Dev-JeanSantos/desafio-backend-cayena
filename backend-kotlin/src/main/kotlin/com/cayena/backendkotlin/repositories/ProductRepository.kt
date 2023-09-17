package com.cayena.backendkotlin.repositories

import com.cayena.backendkotlin.dominio.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Long>
