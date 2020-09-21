package com.demo.repository;

import com.demo.model.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Integer>, ProductRepositoryCustom {

	@Query(value = "select * from PRODUCT p where p.type = ?1", nativeQuery = true)
	List<Product> findProductByType( String type);

}
