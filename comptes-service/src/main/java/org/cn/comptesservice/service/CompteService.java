package org.cn.comptesservice.service;

import org.cn.comptesservice.dtos.CompteDTO;

import java.util.List;

public interface CompteService {
    public CompteDTO save(CompteDTO compteDTO);
    public CompteDTO findById(Long id);
    public List<CompteDTO> findAll();
    public void deleteById(Long id);
}
