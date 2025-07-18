package br.com.dorta.tabelafipe.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Veiculos(
        @JsonAlias("Valor") String valor,
        @JsonAlias("Marca") String marca,
        @JsonAlias("Modelo") String modelo,
        @JsonAlias("AnoModelo") String ano,
        @JsonAlias("Combustivel") String tipoCombustivel
) {
    @Override
    public String toString(){
        return String.format("\nMarca: %s | Modelo: %s | ano: %s | valor: %s | combustivel: %s",
                marca, modelo, ano, valor, tipoCombustivel);
    }
}
