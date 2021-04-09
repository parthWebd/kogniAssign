package com.kog.assignment.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kog.assignment.dto.Offer;
import com.kog.assignment.service.OfferService;

@RestController
@RequestMapping("/collect")
public class OfferController {
	
	
	@Autowired
	private OfferService offerSer;
	
	@GetMapping("/offer")
	public ResponseEntity<List<Offer>> getOffer(){
		LocalDate fr=LocalDate.of(2021, 1, 13);  
		LocalDate to=LocalDate.of(2027, 1, 13);  
		Offer o=new Offer("Parth", "Bangluru", fr, to);
		
		List<Offer> list = offerSer.getAllOffer();
		
		ResponseEntity<List<Offer>> res=new ResponseEntity<>(list, HttpStatus.OK);
		return res;
	}
	
	@PostMapping("/offer")
	public ResponseEntity<Offer> postOffer(@RequestBody Offer offer){
		
		try {
			offerSer.addOffer(offer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ResponseEntity<Offer> res=new ResponseEntity<>(offer, HttpStatus.OK);
		return res;
	}

}
