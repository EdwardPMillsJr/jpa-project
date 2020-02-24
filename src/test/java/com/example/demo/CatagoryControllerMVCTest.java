package com.example.demo;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(CatagoryController.class)
public class CatagoryControllerMVCTest {

	@Resource
	private MockMvc mvc;
	
	@MockBean
	private CatagoryRepository catRepo;
	
	@Mock
	private Catagory catagory;
	
	@Mock
	private Catagory catagory2;
	
	@Test
	public void shouldRouteToSingleTopicView() throws Exception {
		long arbitraryCatagoryId = 2;
		when(catRepo.findById(arbitraryCatagoryId)).thenReturn(Optional.of(catagory));
		mvc.perform(get("/catagory?id=2")).andExpect(view().name(is("catagory")));
	}
	
	@Test
	public void shouldBeOkForSingleCatagory() throws Exception {
		long arbitraryCatagoryId = 2;
		when(catRepo.findById(arbitraryCatagoryId)).thenReturn(Optional.of(catagory));
		mvc.perform(get("/catagory?id=2")).andExpect(status().isOk());
	}

	@Test
	public void shouldNotBeOkayForSingleCatagory() throws Exception {
		mvc.perform(get("/catagory?id=2")).andExpect(status().isNotFound());
	}
	
	@Test
	public void shouldPutSingleCatagoryIntoModel() throws Exception {
		when(catRepo.findById(2L)).thenReturn(Optional.of(catagory));
		mvc.perform(get("/catagory?id=2")).andExpect(model().attribute("catagory", is(catagory)));
	}
	
	@Test
	public void shouldBeOkForAllCatagories() throws Exception {
		mvc.perform(get("/catagories")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldRouteToAllCatagoiesView() throws Exception {
		mvc.perform(get("/catagories")).andExpect(view().name(is("catagory")));
	}
	
	@Test
	public void shouldPutAllCatagoriesIntoModel() throws Exception {
		Collection<Catagory> allCatagories = Arrays.asList(catagory, catagory2);
		when(catRepo.findAll()).thenReturn(allCatagories);
		mvc.perform(get("/catagories")).andExpect(model().attribute("catagory", is(allCatagories)));
	}
}
