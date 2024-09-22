package com.ing.ls;

import com.ing.ls.dto.LoanRequest;
import com.ing.ls.dto.LoanSummary;
import com.ing.ls.entity.Customer;
import com.ing.ls.entity.Loan;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LoanServiceDemoApplicationTests {

	@LocalServerPort
	private int port;

	private String baseUrl = "http://localhost";

	private static RestTemplate restTemplate;

	@Autowired
	private TestH2Repo h2Repository;

	@BeforeAll
	public static void init() {
		restTemplate = new RestTemplate();
	}

	@BeforeEach
	public void setUp() {
		baseUrl = baseUrl.concat(":").concat(port + "").concat("/v1/loan");
	}


	@Test
	@Order(1)
	public void applyLoanIntegrationTest() {
		//request
		ArrayList<Loan> loans= new ArrayList<>();
		loans.add(new Loan(UUID.randomUUID(),10000));
		Customer cust= new Customer(1L, "John Rao", loans);
		LoanRequest loanRequest= new LoanRequest(cust);

		Customer response = restTemplate.postForObject(baseUrl.concat("/apply"), loanRequest, Customer.class);
		assertEquals("John Rao", response.getCustomerName());
		assertEquals(1L, response.getCustomerId());
	}


	@Test
	@Order(2)
	public void getLoanSummaryTest() {
		//request
		LoanSummary response = restTemplate.getForObject(baseUrl.concat("/summary/1"), LoanSummary.class);
		assertEquals("John Rao", response.getCustomerName());
		assertEquals(10000L, response.getTotalLoanApplied());
	}

}
