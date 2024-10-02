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
    boolean existsByUf(String uf);
    boolean existsCidadeByUfAndNome(String uf, String nome);
    boolean existsCidadeByUfAndCapital(String uf, Boolean capital);

    List<Cidade> findByUf(String uf);
}
