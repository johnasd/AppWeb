package com.appweb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appweb.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product , Long> {
	
}
