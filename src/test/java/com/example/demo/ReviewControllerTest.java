package com.example.demo;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

public class ReviewControllerTest {

	@InjectMocks
	private ReviewController reviewTest;
	
	@InjectMocks
	private CatagoryController catTest;
	
	@Mock
	private Review review;
	
	@Mock
	private Review review2;
	
	@Mock
	private ReviewRepository reviewRepo;
	
	@Mock
	private CatagoryRepository catRepo;
	
	@Mock
	private Catagory catagory;
	
	@Mock
	private Catagory catagory2;
	
	@Mock
	private Model model;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldAddSingleReviewToModel() throws ReviewNotFoundException {
		long arbitraryReviewId = 1;
		when(reviewRepo.findById(arbitraryReviewId)).thenReturn(Optional.of(review));
		
		reviewTest.findOneReview(arbitraryReviewId, model);
		verify(model).addAttribute("review", review);
	}
	
	@Test
	public void shouldAddAllReviewsToModel() {
		Collection<Review> allReviews = Arrays.asList(review, review2);
		when(reviewRepo.findAll()).thenReturn(allReviews);
		
		reviewTest.findAllReviews(model);
		verify(model).addAttribute("review", allReviews);
	}
	
	@Test
	public void shouldAddSingleCatagoryToModel()throws CatagoryNotFoundException {
		long arbitraryCatagoryId = 1;
		when(catRepo.findById(arbitraryCatagoryId)).thenReturn(Optional.of(catagory));
		
		catTest.findOneCatagory(arbitraryCatagoryId, model);
		verify(model).addAttribute("catagory", catagory);
	}
	
	@Test
	public void shouldAddAllCatagoriesToModel() {
		Collection<Catagory> allCatagories = Arrays.asList(catagory,catagory2);
		when(catRepo.findAll()).thenReturn(allCatagories);
		
		catTest.findAllCatagories(model);
		verify(model).addAttribute("catagory", allCatagories);
		
	}
}
