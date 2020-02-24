package com.example.demo;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ReviewPopulator implements CommandLineRunner {

	@Resource
	private ReviewRepository reviewRepo;
	
	@Resource
	private CatagoryRepository catRepo;

	@Override
	public void run(String... args) throws Exception {
		Catagory marvelLegends = new Catagory("Marvel Legends");
		marvelLegends = catRepo.save(marvelLegends);
		Catagory videoGames = new Catagory("Video Games");
		videoGames = catRepo.save(videoGames);
	
		Review spiderman = new Review("Spider-Man", "All New Web Slinger!", "/images/spiderman.jpg", "This is a retro rerelease of a "
				+ "popular Spider-Man figure from the 90s. It's an amazing figure with great posablity and plenty of accessories. The pizza piece"
				+ " is also a cool plus", "7/10", marvelLegends);
		spiderman = reviewRepo.save(spiderman);
		Review docOck = new Review("Superior Octopus", "The Doctor Is In!", "/images/Superior1.jpg", "Doctor Octopus is released yet again, but this time, he's \"Superior\"! This figure is an amazing take on"
				+ " Dan Slott's update to Otto Octavious in his recent Amazing Spider-Man run. This figure has all of the movement"
				+ " you'd expect in a typical Marvel Legends Spider-Man figure, plus a back attackment for the arms. Again,"
				+ " like with the previous Otto figure from the Kingpin wave, the arms don't bend. However, There are mods"
				+ " if you are someone to go down thar route. This to me is a must own!", "8/10", marvelLegends);
		docOck = reviewRepo.save(docOck);
		
		Review superSmash = new Review("Super Smash Brothers Ultimate", "The Perfect Game", "/images/ssbu.jfif", "A great game for friends and families to sit around the tive and play for hours."
				+ " With over 75 unique characters, over 800 music tracks, and 108 stages, this game is sure to entertain you for hours. It has a great singleplayer mode as well as fun online play. It's"
				+ " the perfect game!", "10/10", videoGames);
		superSmash = reviewRepo.save(superSmash);
		Review riskofRain2 = new Review("Risk of Rain 2", "From 2D to 3D", "/images/ror2.png", "The developers of RoR2 have do a perfect job of taking the 2D formate of the orignal Risk of Rain and"
				+ " translating it into 3D. Everything just feels right. The upgrades are there, but this time they're displayed on your character's body. The levals are still vast and diverce. The enemies are varied "
				+ "throughout each different level. Plus, they kept the difficulty that many of the hardcore fans will love. There are some returning characters alongside some new ones. So if you played"
				+ " the previous games, you're gonna want to play this one!", "9/10", videoGames);
		riskofRain2 = reviewRepo.save(riskofRain2);
	}
	
	
}
