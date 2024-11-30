package org.cn.comptesservice.controller;

import lombok.AllArgsConstructor;
import org.cn.comptesservice.dtos.CompteDTO;
import org.cn.comptesservice.service.CompteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comptes")
@AllArgsConstructor
public class CompteController {
    CompteService compteService;

    @GetMapping
    public ResponseEntity<List<CompteDTO>> getComptes() {
        System.out.println(":::::::: controller compte getting all :::::::");
        return ResponseEntity.ok(compteService.findAll());
    }

    @PostMapping
    public ResponseEntity<CompteDTO> createCompte(@RequestBody CompteDTO compteDTO) {
        System.out.println(":::::::: controller compte creating :::::::");
        return ResponseEntity.ok(compteService.save(compteDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompteDTO> updateCompte(@RequestBody CompteDTO compteDTO, @PathVariable Long id) {
        System.out.println(":::::::: controller compte updating :::::::");
        compteDTO.setId(id);
        return ResponseEntity.ok(compteService.save(compteDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompteDTO> getCompteById(@PathVariable Long id) {
        System.out.println(":::::::: controller compte getting by id :::::::");
        return ResponseEntity.ok(compteService.findById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteCompteById(@PathVariable Long id) {
        System.out.println(":::::::: controller deleting :::::::");
        compteService.deleteById(id);

    }
}
