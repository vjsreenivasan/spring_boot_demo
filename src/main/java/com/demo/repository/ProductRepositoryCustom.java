package com.demo.repository;

import java.util.List;

import com.demo.model.Product;

public interface ProductRepositoryCustom {
	List<Product> serachProductsBySearchCriteria(String type, Double min_price, Double max_price, String city,
			String property, String color, Integer gb_limit_min, Integer gb_limit_max);

}
