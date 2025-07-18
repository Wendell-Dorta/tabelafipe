package br.com.dorta.tabelafipe.main;

import br.com.dorta.tabelafipe.models.Veiculos;
import br.com.dorta.tabelafipe.services.ConsumoApi;
import br.com.dorta.tabelafipe.services.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private Scanner reader = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados converteDados = new ConverteDados();

    private final String ENDERECO = "https://parallelum.com.br/fipe/api/v1/";

    public void exibeMenu(){
        System.out.println("""
                **** OPÇÕES ****
                Carro
                Moto
                Caminhão
                """);
    }
}
