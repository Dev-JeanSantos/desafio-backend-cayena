package com.cayena.backendkotlin.repositories

import com.cayena.backendkotlin.dominio.Supplier
import org.springframework.data.jpa.repository.JpaRepository

interface RepositorySupplier: JpaRepository<Supplier, Long>
