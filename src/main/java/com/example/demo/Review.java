package com.example.demo;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;

@Entity
public class Review {

	@Id
	@GeneratedValue
	private Long id;
	
	private String description;
	private String name;
	private String image;
	private String reviewScore;
	
	@Lob
	private String content;
	
	@ManyToMany
	private Collection<Catagory> catagory;
	
	public Long getId() {
		return id;
	}
	
	public Object getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getImage() {
		return image;
	}
	
	public String getReviewScore() {
		return reviewScore;
	}
	
	public String getContent() {
		return content;
	}
	
	public Collection<Catagory> getCatagory() {
		return catagory;
	}
	
	public Review() {
		
	}

	public Review(String name, String description, String image, String content, String reviewScore, Catagory...catagory) {
		this.name = name;
		this.description = description;
		this.image = image;
		this.content = content;
		this.reviewScore = reviewScore;
		this.catagory = new HashSet<>(Arrays.asList(catagory));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Review other = (Review) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	

}
