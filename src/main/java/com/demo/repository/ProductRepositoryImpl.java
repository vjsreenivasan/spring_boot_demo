package com.demo.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.demo.model.Product;

public class ProductRepositoryImpl implements ProductRepositoryCustom {
	

	@PersistenceContext private EntityManager em;

	@Override
	public List<Product> serachProductsBySearchCriteria(String type, Double min_price, Double max_price, String city,
			String property, String color, Integer gb_limit_min, Integer gb_limit_max) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Product> cq = cb.createQuery(Product.class);
		Root<Product> prod = cq.from(Product.class);
	    List<Predicate> predicates = new ArrayList<>();
	    if (type != null) {
	        predicates.add(cb.equal(prod.get("type"), type));
	    }
	    if (min_price != null && max_price != null) {
	    	predicates.add(cb.between(prod.get("price"),  min_price, max_price ));
	    }
	    if (min_price != null && max_price == null) {
	        predicates.add(cb.ge(prod.get("price"),  min_price ));
	    }
	    if (min_price != null && max_price == null) {
	        predicates.add(cb.le(prod.get("price"),  max_price ));
	    }
	    if (city != null) {
	    	predicates.add(cb.like(prod.get("store_address"), "%"+ city ));
	    }
	    if (property != null) {
	        predicates.add(cb.like(prod.get("properties"), "%"+ property + "%" ));
	    }
	    if (color != null) {
	        predicates.add(cb.equal(prod.get("properties"), "color:"+ color ));
	    }
	    if (gb_limit_min != null) {
	        predicates.add(cb.like(prod.get("properties"), "gb_limit:"+ gb_limit_min ));
	    }
	    if (gb_limit_max != null) {
	        predicates.add(cb.like(prod.get("properties"), "gb_limit"+ gb_limit_max));
	    }
	    
	    cq.where(predicates.toArray(new Predicate[0]));
//	    TypedQuery<Product> findAllProducts = em.createQuery(cq);
//	    findAllProducts.unwrap(JpaQuery.class).getDatabaseQuery().getSQLString();
//	    System.out.println(findAllProducts.unwrap(org.apache.openjpa.persistence.QueryImpl.class).getQueryString());
	    return em.createQuery(cq).getResultList();
	}

}
