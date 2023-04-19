package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PlataformaStreaming ps = new PlataformaStreaming();
        DadosService meusDados = new DadosService(ps);
        boolean sair = false;

        while (!sair) {
            System.out.println("Bem-vindo ao StreamingApp!");
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Ver catálogo de filmes");
            System.out.println("2 - Ver catálogo de séries");
            System.out.println("3 - Buscar Séries:");
            System.out.println("4 - Ver perfil");
            System.out.println("5 - Sair");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Catálogo de filmes:");
                    // listar filmes
                    break;
                case 2:
                    System.out.println("Catálogo de séries:");
                    meusDados.carregarSeries();
                    break;
                case 3:
                    System.out.println("Buscar Séries:");
                    System.out.println("Digite o nome da Serie:");
                    String serie = scanner.next();
                    System.out.println(ps.buscarSerie(serie));
                    break;
                case 4:
                    System.out.println("Perfil:");
                    meusDados.carregarClientes();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }

            System.out.println();
        }

        scanner.close();
    }

}
