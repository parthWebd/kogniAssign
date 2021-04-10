package com.kog.assignment.IntegrationTest;

import static org.junit.Assert.assertTrue;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.Arrays;

import org.json.JSONException;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.kog.assignment.KognitivAssignmentApplication;
import com.kog.assignment.dto.Offer;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = KognitivAssignmentApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class KognitivAssinmentIT {
	
	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	private Offer offer;
	
	@Before
	public void before() {
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	}

	
	
	@Test
	public void testGetRequest() throws JSONException {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		
		ResponseEntity<String> res = restTemplate.withBasicAuth("Parth", "Parth1").exchange(
				createURLWithPort("/collect/offer"),
				HttpMethod.GET, entity, String.class);
		
//		String expected = "[{\"name\": \"Sam\",\"location\": \"Bangluru\",\"validFrom\": \"2021-01-13\",\"validTo\": \"2027-01-13\"}]";
		
		System.out.println("Response = "+res.getBody());
		
		assertTrue(res.getBody().contains("Sam"));
	}
	
	@Test
	public void testPostRequest() {
		
		HttpHeaders headers1 = new HttpHeaders();
		headers1.set("Content-Type", "application/json");
		headers1.set("Accept", "application/json");
		String o= "{\r\n"
				+ "    \"name\": \"Swati\",\r\n"
				+ "    \"location\": \"Bangluru\",\r\n"
				+ "    \"validFrom\": \"2021-01-13\",\r\n"
				+ "    \"validTo\": \"2027-01-13\"\r\n"
				+ "}";
		HttpEntity<String> entity = new HttpEntity<String>(o, headers1);
		
		ResponseEntity<String> res = restTemplate.withBasicAuth("Parth", "Parth1").exchange(
				createURLWithPort("/collect/offer"),
				HttpMethod.POST, entity, String.class);
		System.out.println("Response Post = "+res.getBody());
		assertTrue(res.getBody().contains("true"));
	}

		
	
	private String createURLWithPort(final String uri) {
		System.out.println("Port = " + port);
		return "http://localhost:" + port + uri;
	}
	
}
