package org.cn.comptesservice.controller;

import lombok.AllArgsConstructor;
import org.cn.comptesservice.dtos.CompteDTO;
import org.cn.comptesservice.service.CompteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/comptes")
@AllArgsConstructor
public class Controllerv2 {
    CompteService compteService;

    @GetMapping
    public ResponseEntity<List<CompteDTO>> getComptes() {
        return ResponseEntity.ok(compteService.findAll());
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
