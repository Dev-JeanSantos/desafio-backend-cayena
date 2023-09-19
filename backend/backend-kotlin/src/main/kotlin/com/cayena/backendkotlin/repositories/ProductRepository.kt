package com.cayena.backendkotlin.repositories

import com.cayena.backendkotlin.dominio.Product
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<Product, Long> {

    @Query("SELECT obj FROM Product obj WHERE obj.name Like CONCAT ('%', :nameProduct ,'%')")
    fun findByProductName(nameProduct: String, pageable: Pageable): Page<Product>
}
