package com.example.demo;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {

	Collection<Review> findByCatagoryContains(Catagory catagory);

	Collection<Review> findByCatagoryId(Long id);
}
