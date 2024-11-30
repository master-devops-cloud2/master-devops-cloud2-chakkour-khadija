package org.cn.comptesservice.controller;

import org.cn.comptesservice.dtos.CompteDTO;
import org.cn.comptesservice.service.CompteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CompteController.class)
public class CompteControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CompteService compteService;

    @InjectMocks
    private CompteController compteController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(compteController).build();
    }

    @Test
    void testGetComptes() throws Exception {
        // Simuler le service pour renvoyer une liste de comptes
        when(compteService.findAll()).thenReturn(List.of(
                new CompteDTO(1L, "Compte1", "Prenom1", "123456", 2000.0),
                new CompteDTO(2L, "Compte2", "Prenom2", "654321", 600.0)
        ));

        // Effectuer la requête GET et vérifier la réponse
        mockMvc.perform(get("/api/v1/comptes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nom").value("Compte1"))
                .andExpect(jsonPath("$[0].prenom").value("Prenom1"))
                .andExpect(jsonPath("$[0].tele").value("123456"))
                .andExpect(jsonPath("$[0].solde").value(2000.0));
    }

    @Test
    void testCreateCompte() throws Exception {
        // Créer un objet CompteDTO pour la requête POST
        CompteDTO compteDTO = new CompteDTO(1L, "Compte1", "Prenom1", "123456", 2000.0);

        // Simuler le service pour renvoyer l'objet créé
        when(compteService.save(any(CompteDTO.class))).thenReturn(compteDTO);

        // Effectuer la requête POST et vérifier la réponse
        mockMvc.perform(post("/api/v1/comptes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"nom\":\"Compte1\",\"prenom\":\"Prenom1\",\"tele\":\"123456\",\"solde\":2000.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nom").value("Compte1"))
                .andExpect(jsonPath("$.prenom").value("Prenom1"))
                .andExpect(jsonPath("$.tele").value("123456"))
                .andExpect(jsonPath("$.solde").value(2000.0));
    }

    @Test
    void testUpdateCompte() throws Exception {
        // Créer un objet CompteDTO pour la mise à jour
        CompteDTO compteDTO = new CompteDTO(1L, "Compte1 Updated", "Prenom1", "123456", 2500.0);

        // Simuler le service pour renvoyer l'objet mis à jour
        when(compteService.save(any(CompteDTO.class))).thenReturn(compteDTO);

        // Effectuer la requête PUT et vérifier la réponse
        mockMvc.perform(put("/api/v1/comptes/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"nom\":\"Compte1 Updated\",\"prenom\":\"Prenom1\",\"tele\":\"123456\",\"solde\":2500.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nom").value("Compte1 Updated"))
                .andExpect(jsonPath("$.prenom").value("Prenom1"))
                .andExpect(jsonPath("$.tele").value("123456"))
                .andExpect(jsonPath("$.solde").value(2500.0));
    }

    @Test
    void testGetCompteById() throws Exception {
        // Simuler le service pour renvoyer un compte par ID
        CompteDTO compteDTO = new CompteDTO(1L, "Compte1", "Prenom1", "123456", 2000.0);
        when(compteService.findById(1L)).thenReturn(compteDTO);

        // Effectuer la requête GET par ID et vérifier la réponse
        mockMvc.perform(get("/api/v1/comptes/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nom").value("Compte1"))
                .andExpect(jsonPath("$.prenom").value("Prenom1"))
                .andExpect(jsonPath("$.tele").value("123456"))
                .andExpect(jsonPath("$.solde").value(2000.0));
    }

    @Test
    void testDeleteCompte() throws Exception {
        // Effectuer la requête DELETE
        mockMvc.perform(delete("/api/v1/comptes/{id}", 1L))
                .andExpect(status().isOk());

        // Vérifier que le service a bien appelé la méthode deleteById
        verify(compteService, times(1)).deleteById(1L);
    }
}
