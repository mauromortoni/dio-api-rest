package me.dio.controller.dto;

import me.dio.model.Estado;

public record EstadoDto(Long id, String nome, String sigla) {
    public EstadoDto(Estado model) {
        this(
                model.getId(),
                model.getNome(),
                model.getSigla()
        );
    }

    public Estado toModel() {
        Estado model = new Estado();

        model.setId(this.id);
        model.setNome(this.nome);
        model.setSigla(this.sigla);

        return model;
    }
}
