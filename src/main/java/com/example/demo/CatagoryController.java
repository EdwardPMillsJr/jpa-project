package com.example.demo;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CatagoryController {

	@Resource
	CatagoryRepository catRepo;
	
	@RequestMapping("/catagory")
	public String findOneCatagory(@RequestParam(value="id") long id, Model model) throws CatagoryNotFoundException {
		Optional<Catagory> catagory = catRepo.findById(id);
		
		if(catagory.isPresent()) {
			model.addAttribute("catagory", catagory.get());
			return "catagory";
		}
		throw new CatagoryNotFoundException();
		
	}

	@RequestMapping("/catagories")
	public String findAllCatagories(Model model) {
		model.addAttribute("catagories", catRepo.findAll());
		return ("catagories");
	}


}
