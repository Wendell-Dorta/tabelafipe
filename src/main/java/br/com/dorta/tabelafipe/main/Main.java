package br.com.dorta.tabelafipe.main;

import br.com.dorta.tabelafipe.models.Dados;
import br.com.dorta.tabelafipe.models.Modelos;
import br.com.dorta.tabelafipe.models.Veiculos;
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

        System.out.println("\nInforme o código da marca: ");
        var codigoMarca = reader.nextLine();

        endereco = endereco + codigoMarca + "/modelos/";
        json = consumoApi.obterDados(endereco);
        var listaModelos = converteDados.obterDados(json, Modelos.class);

        System.out.println("\nModelos da marca: ");
        listaModelos.modelos().stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

        System.out.println("Digite um trecho do nome do modelo do veiculo: ");
        var nomeVeiculo = reader.nextLine();

        List<Dados> modelosFiltrados = listaModelos.modelos().stream()
                .filter(m -> m.nome().toLowerCase().contains(nomeVeiculo.toLowerCase()))
                .collect(Collectors.toList());

        System.out.println("\nModelos Filtrados: ");
        modelosFiltrados.forEach(System.out::println);

        System.out.println("Digite por favor o código do modelo para buscar os valores de avaliação: ");
        var codigoModelo = reader.nextLine();

        endereco = endereco + codigoModelo + "/anos/";
        json = consumoApi.obterDados(endereco);
        List<Dados> anos = converteDados.obterLista(json, Dados.class);
        List<Veiculos> veiculos = new ArrayList<>();

        for (int i = 0; i < anos.size(); i++) {
            var enderecoAnos = endereco + anos.get(i).codigo();
            json = consumoApi.obterDados(enderecoAnos);
            Veiculos veiculo = converteDados.obterDados(json, Veiculos.class);
            veiculos.add(veiculo);
        }

        System.out.println("\nTodos os veículos filtrados com avaliações por ano: ");
        veiculos.forEach(System.out::println);
    }
}
