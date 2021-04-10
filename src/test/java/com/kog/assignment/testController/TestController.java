package com.kog.assignment.testController;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.kog.assignment.controller.OfferController;
import com.kog.assignment.dto.Offer;
import com.kog.assignment.service.OfferService;

@WebMvcTest(OfferController.class)
public class TestController {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private OfferService offerService;
	@Mock
	private OfferService ser;
	
	
	private Offer offer;
	
	
	@BeforeEach
	public void setup() {
		LocalDate fr=LocalDate.of(2021, 1, 13);  
		LocalDate to=LocalDate.of(2027, 1, 13);  
		offer=new Offer("Swati", "Bangluru", fr, to);
	}
	
	
	@WithMockUser(value = "spring")
	@Test
	public void testGetAllOffer() throws Exception {
		List<Offer> value=new ArrayList<Offer>();
		value.add(offer);
		when(offerService.getAllOffer()).thenReturn(value);
		RequestBuilder request= MockMvcRequestBuilders
				.get("/collect/offer")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult response = mockMvc.perform(request)
				.andExpect(status().isOk())
				.andReturn();
		
		System.out.println("JJJJJJJJJJJJAOOOOO = "+response.getResponse().getContentAsString());
		assertTrue(response.getResponse().getContentAsString().contains("Bangluru"));
	}
	
	
	@WithMockUser(value = "spring")
	@Test
	public void testAddOffer() throws Exception {
		
		when(offerService.addOffer(any())).thenReturn(offer);
		
		RequestBuilder request= MockMvcRequestBuilders
				.post("/collect/offer")
				.content("{\r\n"
						+ "    \"name\": \"Swati\",\r\n"
						+ "    \"location\": \"Bangluru\",\r\n"
						+ "    \"validFrom\": \"2021-01-13\",\r\n"
						+ "    \"validTo\": \"2027-01-13\"\r\n"
						+ "}")
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult response = mockMvc.perform(request)
				.andExpect(status().isOk())
				.andReturn();
		
		assertTrue(response.getResponse().getContentAsString().contains("true"));
		
	}
	
	
}
