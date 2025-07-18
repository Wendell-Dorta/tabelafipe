package br.com.dorta.tabelafipe.main;

import br.com.dorta.tabelafipe.models.Dados;
import br.com.dorta.tabelafipe.models.Modelos;
import br.com.dorta.tabelafipe.services.ConsumoApi;
import br.com.dorta.tabelafipe.services.ConverteDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private Scanner reader = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados converteDados = new ConverteDados();

    private final String ENDERECO = "https://parallelum.com.br/fipe/api/v1/";

    public void exibeMenu(){
        System.out.println("""
                **** OPÇÕES ****
                1 - Carro
                2 - Moto
                3 - Caminhão
                """);

        System.out.println("Escolha uma opção de veiculos");
        var opcao = reader.nextLine();
        String tipoVeiculo = "";
        switch (opcao) {
            case "1":
                tipoVeiculo = "carros";
                break;
            case "2":
                tipoVeiculo = "motos";
            case "3":
                tipoVeiculo = "caminhoes";
        }
        var endereco = ENDERECO + tipoVeiculo + "/marcas/";
        var json = consumoApi.obterDados(endereco);
        System.out.println(json);

        System.out.println("\nMarcas: ");
        var marcas = converteDados.obterLista(json, Dados.class);
        marcas.stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);


    }
}
