package com.example.demo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class JPAMappingTest {

	@Resource
	private TestEntityManager entityManager;
	
	@Resource
	private CatagoryRepository catRepo;
	
	@Resource
	private ReviewRepository reviewRepo;
	
	
	@Test
	public void shouldSaveAndLoadCatagory() {
		Catagory catagory = catRepo.save(new Catagory("Marvel Legends"));
		Long catId = catagory.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Catagory> result = catRepo.findById(catId);
		catagory = result.get();
		assertThat(catagory.getName(), is("Marvel Legends"));
	}
	
	@Test
	public void shouldGenerateCatagoryID() {
		Catagory catagory = catRepo.save(new Catagory("Marvel Legends"));
		Long catId = catagory.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		assertThat(catId, is(greaterThan(0L)));
	}
	
	@Test
	public void shouldSaveAndLoadReview() {
		Review review = new Review("review name", "description", "image", "cool stuff", "10");
		review = reviewRepo.save(review);
		Long reviewId = review.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Review> result = reviewRepo.findById(reviewId);
		review = result.get();
		assertThat(review.getName(), is("review name"));
	}
	
	@Test
	public void shouldEstablishReviewtoCatagoryRelationship() {
		Catagory marvelLegends = catRepo.save(new Catagory("Marvel Legends"));
		Catagory videoGames = catRepo.save(new Catagory("Video Games"));

		Review review = new Review("Marvel Legends", "description", "image", "cool stuff", "10", marvelLegends, videoGames);
		review = reviewRepo.save(review);
		Long reviewId = review.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Review> result = reviewRepo.findById(reviewId);
		review = result.get();
		
		assertThat(review.getCatagory(), containsInAnyOrder(marvelLegends,videoGames));
	}
	
	@Test
	public void shouldFindReviewForCatagory() {
		Catagory marvelLegends = catRepo.save(new Catagory ("Marvel Legends"));
		
		Review spiderMan = reviewRepo.save(new Review("Spiderman", "Description", "image", "cool stuff", "10", marvelLegends));
		Review docOck = reviewRepo.save(new Review("Doctor Octopus", "Description", "image", "cool stuff", "10", marvelLegends));
		
		entityManager.flush();
		entityManager.clear();
		
		Collection<Review> reviewsForCatagory = reviewRepo.findByCatagoryContains(marvelLegends);
		
		assertThat(reviewsForCatagory, containsInAnyOrder(spiderMan, docOck));
	}
	
	@Test
	public void shouldFindReviewForCatagoryId() {
		Catagory marvelLegends = catRepo.save(new Catagory("Marvel Legends"));
		Long catId = marvelLegends.getId();
		
		Review spiderMan = reviewRepo.save(new Review("Spiderman", "Description", "image", "cool stuff", "10", marvelLegends));
		Review docOck = reviewRepo.save(new Review("Doctor Octopus", "Description", "image", "cool stuff", "10", marvelLegends));
		
		entityManager.flush();
		entityManager.clear();
		
		Collection<Review> reviewsForCatagory = reviewRepo.findByCatagoryId(catId);
		assertThat(reviewsForCatagory, containsInAnyOrder(spiderMan, docOck));
	}
}
