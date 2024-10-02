package me.dio.controller.dto;

import me.dio.model.Cidade;
import me.dio.model.Estado;

public record CidadeDto(Long id, String nome, String uf, Boolean capital) {
    public CidadeDto(Cidade model) {
        this(
            model.getId(),
            model.getNome(),
            model.getUf(),
            model.getCapital()
        );
    }

    public Cidade toModel() {
        Cidade model = new Cidade();

        model.setId(this.id);
        model.setNome(this.nome);
        model.setUf(this.uf);
        model.setCapital(this.capital);

        return model;
    }
}
