package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.Product;
import com.demo.repository.ProductRepository;

@RestController
@RequestMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
public class ProductController {
	
	@Autowired
    private ProductRepository productRepo;
	
    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    Product getEmployee(@PathVariable Integer id){
        return  productRepo.findById(id).get();
    }
    
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public List<Product> product(@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "min_price", required = false) Double min_price,
			@RequestParam(value = "max_price", required = false) Double max_price,
			@RequestParam(value = "city", required = false) String city,
			@RequestParam(value = "property", required = false) String property,
			@RequestParam(value = "property:color", required = false) String color,
			@RequestParam(value = "property:gb_limit_min", required = false) Integer gb_limit_min,
			@RequestParam(value = "property:gb_limit_max", required = false) Integer gb_limit_max) {
        return productRepo.serachProductsBySearchCriteria(type, min_price, max_price, city, property, color, gb_limit_min, gb_limit_max);
	}

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    List<Product> getAllProducts(){
        return productRepo.findAll();
    }
    
    @RequestMapping(value = "/product_type", method = RequestMethod.GET)
    List<Product> getByType(){
        return productRepo.findProductByType("phone");
    }

}