package org.cn.comptesservice.mappers;

import lombok.AllArgsConstructor;
import org.cn.comptesservice.dtos.CompteDTO;
import org.cn.comptesservice.entities.Compte;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompteMapper {

    public CompteDTO fromCompte(Compte compte) {
       CompteDTO compteDTO = new CompteDTO();
        BeanUtils.copyProperties(compte, compteDTO);
        return compteDTO;
    }

    public Compte toCompte(CompteDTO compteDTO) {
        Compte compte = new Compte();
        BeanUtils.copyProperties(compteDTO, compte);
        return compte;
    }
}
