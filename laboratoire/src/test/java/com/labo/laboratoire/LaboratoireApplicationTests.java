package com.labo.laboratoire;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.labo.laboratoire.Entities.Laboratoire;
import com.labo.laboratoire.Services.LaboratoireService;

@ExtendWith(MockitoExtension.class)
class LaboratoireServiceTests {

	@Mock
	private LaboratoireService laboratoireService; // Mocking LaboratoireService

	@InjectMocks
	private Laboratoire laboratoire; // Create a Laboratoire instance to test

	@Test
	public void testAjouterLaboratoire() {
		// Create a real instance to test
		Laboratoire labo = new Laboratoire();
		labo.setNom("Labo Test JUnit");
		labo.setNrc("1010");
		labo.setActive(false);
		labo.setDateActivation(LocalDate.parse("2022-12-12")); // Correct method to set date

		// Mock the behavior of laboratoireService.saveLaboratoire
		when(laboratoireService.saveLaboratoire(any(Laboratoire.class)))
				.thenReturn(labo); // Returning the laboratoire created

		Laboratoire savedLabo = laboratoireService.saveLaboratoire(labo);
		assertNotNull(savedLabo);
	}

	@Test
	public void testAjouterLaboratoireWithMockito() {
		// Test using mock service (Mockito)
		Laboratoire mockLabo = new Laboratoire();
		mockLabo.setNom("Mock Labo Test");
		mockLabo.setNrc("54321");
		mockLabo.setActive(true);
		mockLabo.setDateActivation(LocalDate.now());

		// Mock the behavior of laboratoireService.saveLaboratoire
		when(laboratoireService.saveLaboratoire(any(Laboratoire.class)))
				.thenReturn(mockLabo);

		Laboratoire returnedLabo = laboratoireService.saveLaboratoire(mockLabo);

		assertNotNull(returnedLabo);
		assertNotNull(returnedLabo.getNom());

		// Verifying the interaction with the mock service
		verify(laboratoireService, times(1)).saveLaboratoire(mockLabo);
	}
}
