package me.dio.service.impl;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.Converter;
import me.dio.model.Cidade;
import me.dio.service.exception.NotFoundException;
import me.dio.repository.CidadeRepository;
import org.modelmapper.internal.util.Strings;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import me.dio.service.interfaces.CidadeService;
import me.dio.util.ObjectMapperUtils;
import me.dio.controller.dto.CidadeDto;
import org.springframework.transaction.annotation.Transactional;
import me.dio.service.exception.BadRequestException;

import static java.util.Optional.ofNullable;

@Service
public class CidadeServiceImpl implements CidadeService {
	private final CidadeRepository repository ;

	public CidadeServiceImpl(CidadeRepository repository) {
		this.repository = repository;
	}

	//---------------------------------------------------------
	/** Método que retorna todas as cidades cadastradas      */
	//---------------------------------------------------------
	@Override
	@Transactional(readOnly = true)
	public Page<CidadeDto> AllCidades(Pageable pageable) {
		Page<Cidade> pageCidades = repository.findAll(pageable);
		return pageCidades.map(CidadeDto::new);
	}

	@Transactional(readOnly = true)
	public Optional<CidadeDto> CidadeById(Long id) {
		if (!repository.existsById(id)) {
			throw new NotFoundException();
		}

		return repository
				.findById(id)
				.map(Cidade::toRecord);
	}

	//----------------------------------------------------------
	/** Método que retorna as cidades da uf cadastrada        */
	//----------------------------------------------------------
	@Transactional(readOnly = true)
	public List<CidadeDto> CidadesByUf(String uf) {
		if (repository.existsByUf(uf)) {
			List<Cidade> listCidades = repository.findByUf(uf);
			return listCidades.stream().map(CidadeDto::new).toList();
		}

		throw new NotFoundException();
	}

	//----------------------------------------------------------
	/** Método chamado para incluir uma nova cidade */
	//----------------------------------------------------------
	@Override
	@Transactional
	public Cidade createCidade(CidadeDto dto) {
		if (repository.existsCidadeByUfAndNome(dto.uf(), dto.nome())) throw new BadRequestException();
		if (dto.capital() == true) {
			if (repository.existsCidadeByUfAndCapital(dto.uf(), true))
				throw new BadRequestException();
		}

		Cidade cidade = dto.toModel();
		return repository.saveAndFlush(dto.toModel());
	}

	//----------------------------------------------------------
	/** Método chamado para alterar os dados de uma cidade */
	//----------------------------------------------------------
	@Override
	@Transactional
	public CidadeDto updateCidade(Long id, CidadeDto dto) {
		if (repository.existsById(id)) {
			var cidade = repository.findById(dto.id()).get();

//			cidade = ObjectMapperUtils.updateNonNullFields(dto, cidade);
			Optional<String> nome = Optional.ofNullable(dto.nome());
			Optional<String> uf = Optional.ofNullable(dto.uf());
			Optional<Boolean> capital = Optional.ofNullable(dto.capital());

			if (nome.isPresent()) cidade.setNome(nome.get());
			if (uf.isPresent()) cidade.setUf(uf.get());
			if (capital.isPresent()) cidade.setCapital(capital.get());

			cidade = repository.saveAndFlush(cidade);

			return cidade.toRecord();
		} else {
			throw new NotFoundException();
		}
	}

	//----------------------------------------------------------
	/** Método chamado para excluir uma cidade */
	//----------------------------------------------------------
	@Override
	@Transactional
	public void deleteCidade(Long id) {
		if (id == null) {
			throw new BadRequestException();
		}

		repository.deleteById(id);
	}
}
