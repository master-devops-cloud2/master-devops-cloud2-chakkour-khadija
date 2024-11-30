package org.cn.comptesservice.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CompteDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String tel;
    private Double solde;
}
