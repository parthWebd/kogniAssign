package com.kog.assignment.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

	@GetMapping(value = "/offer",produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Offer>> getOffer() {
		List<Offer> list = offerSer.getAllOffer();
		ResponseEntity<List<Offer>> res = new ResponseEntity<>(list, HttpStatus.OK);
		return res;
	}
	

	@PostMapping("/offer")
	public String postOffer(@RequestBody Offer offer) {
		System.out.println("To add offer"+offer);
		
		Offer of = null;
		try {
			of = offerSer.addOffer(offer);
//			System.out.println("Inside controller " + of);
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (of != null)
			return "Success: true";
		else
			return "Success: false";
	}

}
