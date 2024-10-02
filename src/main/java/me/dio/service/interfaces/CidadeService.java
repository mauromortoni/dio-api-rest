package me.dio.service.interfaces;

import me.dio.controller.dto.CidadeDto;
import me.dio.model.Cidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CidadeService extends CrudService<Cidade, Long> {
    //---------------------------------------------------------
    Page<CidadeDto> AllCidades(Pageable pageable);
    Optional<CidadeDto> CidadeById(Long id);
    List<CidadeDto> CidadesByUf(String uf);
    Cidade createCidade(CidadeDto dto);
    CidadeDto updateCidade(Long id, CidadeDto dto);
    void deleteCidade(Long idCidade);
}
