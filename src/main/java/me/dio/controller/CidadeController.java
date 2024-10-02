package me.dio.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import me.dio.controller.dto.CidadeDto;
import me.dio.service.interfaces.CidadeService;

@RestController
@CrossOrigin
@RequestMapping("/cidades/v1")
@Tag(name = "Cidades Controller", description = "RESTful API for managing cidades.")
public class CidadeController {
	private final CidadeService service ;

	public CidadeController(CidadeService service) {
		this.service = service;
	}

	@GetMapping("/AllCidades")
	@Operation(summary = "Get all cidades", description = "Retrieve a list of all registered cidades")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Operation successful")
	})
	public Page<CidadeDto> AllCidades(@ParameterObject @PageableDefault(size = 20, sort = "nome") Pageable pageable) {
		return service.AllCidades(pageable);
	}
// ?page=0&size=20&sort=nome,email,asc
	@GetMapping("/CidadeById/{id}")
	@Operation(summary = "Get a cidade by ID", description = "Retrieve a specific cidade based on its ID")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Operation successful"),
		@ApiResponse(responseCode = "404", description = "Cidade not found")
	})
	public ResponseEntity<CidadeDto> CidadeById(@PathVariable Long id) {
		var cidadeDto = service.CidadeById(id);

		return ResponseEntity.ok(cidadeDto.get());
	}

	@GetMapping("/CidadesByUf/{uf}")
	@Operation(summary = "Get a cidade by UF", description = "Retrieve a specific cidade based on its UF")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Operation successful"),
			@ApiResponse(responseCode = "404", description = "Cidade not found")
	})
	public ResponseEntity<List<CidadeDto>> findByUf(@PathVariable(required = true) String uf) {
		var cidades = service.CidadesByUf(uf);
//		var cidadesDto = cidades.stream().map(CidadeDto::new).collect(Collectors.toList());

		return ResponseEntity.ok(cidades);
	}

	@PostMapping("/createCidade")
	@Operation(summary = "Create a new cidade", description = "Create a new cidade and return the created cidade's data")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Cidade created successfully"),
			@ApiResponse(responseCode = "422", description = "Invalid user data provided")
	})
	public ResponseEntity<CidadeDto> createCidade(@RequestBody(required = true) CidadeDto cidadeDto) {
		service.createCidade(cidadeDto);
		return new ResponseEntity<>(HttpStatus.CREATED);
//		return ResponseEntity.created(location).body(service.createCidade(cidadeDto));
	}	
	
	@PutMapping("/updateCidade/{id}")
	@Operation(summary = "Update a cidade", description = "Update the data of an existing cidade based on its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cidade updated successfully"),
			@ApiResponse(responseCode = "404", description = "Cidade not found"),
			@ApiResponse(responseCode = "422", description = "Invalid cidade data provided")
	})
	public ResponseEntity<CidadeDto> updateCidade(@PathVariable(required = true) Long id,
											  @RequestBody(required = true) CidadeDto cidadeDto) {
		service.updateCidade(id, cidadeDto);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/deleteCidade/{id}")
	@Operation(summary = "Delete a cidade", description = "Delete an existing cidade based on its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Cidade deleted successfully"),
			@ApiResponse(responseCode = "404", description = "Cidade not found")
	})
	public ResponseEntity<Void> excluirCidade(@PathVariable(required = true) Long id) {
		service.deleteCidade(id);
		return ResponseEntity.noContent().build();
	}
}
