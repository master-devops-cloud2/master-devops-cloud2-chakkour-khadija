package org.cn.comptesservice.service.imp;

import lombok.AllArgsConstructor;
import org.cn.comptesservice.dtos.CompteDTO;
import org.cn.comptesservice.mappers.CompteMapper;
import org.cn.comptesservice.repository.CompteRepository;
import org.cn.comptesservice.service.CompteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class CompteServiceImpl implements CompteService {

    private final CompteRepository compteRepository;
    private final CompteMapper compteMapper;

    @Override
    public CompteDTO save(CompteDTO compteDTO) {
        System.out.println(":::::: saving compte ::::::::");
        return compteMapper.fromCompte(compteRepository.save(compteMapper.toCompte(compteDTO)));
    }

    @Override
    public CompteDTO findById(Long id) {
        System.out.println(":::::: finding compte ::::::::");
        return compteMapper.fromCompte(compteRepository.findById(id).orElse(null));
    }

    @Override
    public List<CompteDTO> findAll() {
        System.out.println(":::::: getting all comptes ::::::::");
        return compteRepository.findAll().stream().map(compteMapper::fromCompte).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        System.out.println(":::::: deleting compte ::::::::");
        compteRepository.deleteById(id);

    }
}
