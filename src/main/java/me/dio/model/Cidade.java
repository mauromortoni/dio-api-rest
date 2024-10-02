package me.dio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import me.dio.controller.dto.CidadeDto;

import java.io.Serializable;

//-------------------------------------------------
/** Entidade que guarda os dados de uma cidade */
//-------------------------------------------------
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cidade")
public class Cidade implements Serializable {
    private static final long serialVersionUID = -2492630916137536785L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @NotBlank
    @Column(name = "NOME", length = 100, nullable = false)
    private String nome;

    @NotBlank
    @Column(name = "UF", length = 2, nullable = false)
    private String uf;

    @Column(name = "capital", nullable = false)
    private Boolean capital;

    public CidadeDto toRecord() {
        return new CidadeDto(
                id,
                nome,
                uf,
                capital
        );
    }
}

