package com.labo.utilisateur;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.labo.utilisateur.Entities.Utilisateur;
import com.labo.utilisateur.Services.UtilisateurService;

@SpringBootTest
class UtilisateurApplicationTests {

	@Mock
	private UtilisateurService utilisateurService; // Mocking UtilisateurService

	@InjectMocks
	private Utilisateur utilisateur; // Creating a Utilisateur instance to test

	@Test
	void contextLoads() {
	}

	@Test
	public void testAddUtilisateur() {
		// Create a real instance to test
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setEmail("testuser@example.com");
		utilisateur.setFkIdLaboratoire(1L);
		utilisateur.setNomComplet("Test User");
		utilisateur.setProfession("Engineer");
		utilisateur.setNumTel("1234567890");
		utilisateur.setImageurl("http://example.com/image.png");
		utilisateur.setSignature("signature".getBytes()); // Sample signature
		utilisateur.setRole("User");

		// Mock the behavior of utilisateurService.saveUser
		when(utilisateurService.saveUser(any(Utilisateur.class)))
				.thenReturn(utilisateur); // Returning the created utilisateur

		Utilisateur savedUtilisateur = utilisateurService.saveUser(utilisateur);
		assertNotNull(savedUtilisateur);
		assertNotNull(savedUtilisateur.getEmail());
		assertNotNull(savedUtilisateur.getNomComplet());
	}

	@Test
	public void testAddUtilisateurWithMockito() {
		// Test using mock service (Mockito)
		Utilisateur mockUtilisateur = new Utilisateur();
		mockUtilisateur.setEmail("mockuser@example.com");
		mockUtilisateur.setFkIdLaboratoire(2L);
		mockUtilisateur.setNomComplet("Mock User");
		mockUtilisateur.setProfession("Scientist");
		mockUtilisateur.setNumTel("0987654321");
		mockUtilisateur.setImageurl("http://example.com/mockimage.png");
		mockUtilisateur.setSignature("mocksignature".getBytes()); // Mocking with a sample byte array
		mockUtilisateur.setRole("Admin");

		// Mock the behavior of utilisateurService.saveUser
		when(utilisateurService.saveUser(any(Utilisateur.class)))
				.thenReturn(mockUtilisateur);

		Utilisateur returnedUtilisateur = utilisateurService.saveUser(mockUtilisateur);

		assertNotNull(returnedUtilisateur.getEmail());
		assertNotNull(returnedUtilisateur.getNomComplet());

		// Verifying the interaction with the mock service
		verify(utilisateurService, times(1)).saveUser(mockUtilisateur);
	}
}
