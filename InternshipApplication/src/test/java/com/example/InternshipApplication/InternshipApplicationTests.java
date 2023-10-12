package com.example.InternshipApplication;

import com.example.InternshipApplication.model.DTO.ApplicantDTO;
import com.example.InternshipApplication.service.ApplicantService;
import com.example.InternshipApplication.service.ApplicantServiceImpl;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class InternshipApplicationTests {


	@Test
	void contextLoads() {
	}

	private ApplicantService applicantService;

	@BeforeEach
	public void setUp() {
		applicantService = new ApplicantServiceImpl(null, null);
	}

	@Test
	public void testValidateApplicantDTOData() {
		// Test with valid data
		ApplicantDTO validApplicantDTO = new ApplicantDTO();
		validApplicantDTO.setFirstName("John");
		validApplicantDTO.setLastName("Doe");
		validApplicantDTO.setEmail("john.doe@example.com");
		validApplicantDTO.setAddress1("123 Main St");
		validApplicantDTO.setPhoneNumber("1234567890");
		validApplicantDTO.setCity("New York");
		validApplicantDTO.setCountry("USA");
		validApplicantDTO.setState("NY");

		assertTrue(applicantService.validateApplicantDTOData(validApplicantDTO));
	}
	@Test
	public void testInvalidApplicationDTOData(){
		ApplicantDTO invalidApplicantDTO = new ApplicantDTO();
		invalidApplicantDTO.setFirstName("j"); // Invalid: First name is too short
		invalidApplicantDTO.setLastName("Doe");
		invalidApplicantDTO.setEmail("john.doe.example.com"); // Invalid: Invalid email format
		invalidApplicantDTO.setAddress1(""); // Invalid: Address is blank
		invalidApplicantDTO.setPhoneNumber("12345"); // Invalid: Phone number length is less than 10

		assertFalse(applicantService.validateApplicantDTOData(invalidApplicantDTO));
	}
}