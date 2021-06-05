package com.ikea.wms.api.dao.repository;

import com.ikea.wms.api.dao.entity.product.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface ProductsRepository extends JpaRepository<Products, BigInteger> {
}
