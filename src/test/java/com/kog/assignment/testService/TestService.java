package com.kog.assignment.testService;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kog.assignment.dao.OfferRepo;
import com.kog.assignment.dto.Offer;
import com.kog.assignment.service.OfferService;
import com.kog.assignment.service.ServiceHelpInt;

@ExtendWith(MockitoExtension.class)
public class TestService {

	@Mock
	private OfferRepo offerRepo;
	
	@Mock
	private ServiceHelpInt serviceHelp;
	
	@InjectMocks
	private OfferService service;
	
	private Offer offer;
	
	
	@BeforeEach
	public void setup() {
		LocalDate fr=LocalDate.of(2021, 1, 13);  
		LocalDate to=LocalDate.of(2027, 1, 13);  
		offer=new Offer("Parth", "Bangluru", fr, to);
	}
	
	@Test
	void testGetAllOffer() {
		
		List<Offer> value=new ArrayList<Offer>();
		value.add(offer);
		when(offerRepo.findAll()).thenReturn(value);
		
		List<Offer> list=service.getAllOffer();
		String exp=list.get(0).getName();
		
		assertEquals(exp, "Parth");
	}
	
	@Test
	void testAddOffer() {
		try {
			service.addOffer(offer);
			verify(offerRepo).save(offer);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	@Test
	void testExceptionInAddOffer() {
		boolean check=false;
		try {
			when(serviceHelp.getDownloadedImage()).thenThrow(IOException.class);
			service.addOffer(offer);
		} catch (IOException e) {
			check=true;
		}
		assertTrue(check);
	}
	
}
