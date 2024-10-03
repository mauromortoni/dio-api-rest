package me.dio.repository;

import me.dio.controller.dto.CidadeDto;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import me.dio.model.Cidade;

import java.util.List;

//----------------------------------------------
/** Repositório para entidade Cidade */
//----------------------------------------------
@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
    boolean existsByEstado_Sigla(String sigla);
    boolean existsCidadeByNomeAndEstadoSigla(String nome, String sigla);
    boolean existsCidadeByCapitalAndEstadoSigla(Boolean capital, String sigla);

    List<Cidade> findByEstado_Sigla(String sigla);
}
