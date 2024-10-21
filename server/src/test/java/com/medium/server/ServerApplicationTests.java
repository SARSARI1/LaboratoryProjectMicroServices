package com.medium.server;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.medium.server.Services.LaboratoireService;
import com.medium.server.Entities.Laboratoire;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ServerApplicationTests {

	@Autowired
	private LaboratoireService laboratoireService;
	@Mock
	private LaboratoireService mockLaboratoireService; // Mocking LaboratoireService

	@InjectMocks
	private Laboratoire mockLaboratoire; // Inject mock into this object
	@Test
	public void testAjouterLaboratoire() {
		Laboratoire labo = new Laboratoire();
		labo.setNom("Labo Test JUnit");
		labo.setNrc("1010");
		labo.setActive(false);
		labo.setDateActivation(LocalDate.parse("2022-12-12")); // Correct method to set date
		Laboratoire savedLabo = laboratoireService.saveLaboratoire(labo);
		assertNotNull(savedLabo.getId());
	}

	@Test
	public void testAjouterLaboratoireWithMockito() {
		// Test using mock service (Mockito)
		Laboratoire mockLabo = new Laboratoire();
		mockLabo.setId(1L); // Simulating the behavior
		mockLabo.setNom("Mock Labo Test");
		mockLabo.setNrc("54321");
		mockLabo.setActive(true);
		mockLabo.setDateActivation(LocalDate.now());

		// Mock the behavior of laboratoireService.saveLaboratoire
		when(mockLaboratoireService.saveLaboratoire(any(Laboratoire.class)))
				.thenReturn(mockLabo);

		Laboratoire returnedLabo = mockLaboratoireService.saveLaboratoire(mockLabo);

		assertNotNull(returnedLabo.getId());
		assertNotNull(returnedLabo.getNom());

		// Verifying the interaction with the mock service
		verify(mockLaboratoireService, times(1)).saveLaboratoire(mockLabo);
	}
}