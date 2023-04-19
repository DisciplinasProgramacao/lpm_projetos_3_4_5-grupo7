package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DadosService {
  private final PlataformaStreaming plataformaStreaming;
  private final List<String> idDeSeries;

  public DadosService(PlataformaStreaming ps) {
    plataformaStreaming = ps;
    idDeSeries = new ArrayList<>();
  }

  public void show() {
    criarArrayDeClientes().forEach(System.out::println);
    criarArrayDeSeries().forEach(System.out::println);
    criarArrayDeAudiencias().forEach(System.out::println);
  }

  public void carregarClientes() {
    List<String> clientes = criarArrayDeClientes();

    clientes.forEach(clienteLine -> {
      String[] dados = splitLine(clienteLine);
      String login = dados[1];
      String senha = dados[2];
      plataformaStreaming.adicionarCliente(new Cliente(login, senha));
    });
  }

  public void carregarSeries() {
    List<String> series = criarArrayDeSeries();

    series.forEach(serieLine -> {
      String[] dados = splitLine(serieLine);
      String id = dados[0];
      String nome = dados[1];
      idDeSeries.add(id);
      plataformaStreaming.adicionarSerie(new Serie(nome));
    });
  }

  private List<String> criarArrayDeClientes() {
    String arquivoCliente = "codigo/POO_Series_2023/POO_Espectadores.csv";
    return carregarDados(arquivoCliente);
  }

  private List<String> criarArrayDeSeries() {
    String nomeArquivo = "codigo/POO_Series_2023/POO_Series.csv";
    return carregarDados(nomeArquivo);
  }

  private List<String> criarArrayDeAudiencias() {
    String nomeArquivo = "codigo/POO_Series_2023/POO_Audiencia.csv";
    return carregarDados(nomeArquivo);
  }

  private List<String> carregarDados(String nomeArquivo) {
    List<String> dados = new ArrayList<>();
    File arquivo;

    try {
      arquivo = new File(nomeArquivo);
      if (!arquivo.exists()) {
        return null;
      }

      Scanner scanner = new Scanner(arquivo);

      if (!scanner.hasNext()) {
        scanner.close();
        return null;
      }

      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        dados.add(line);
      }

      scanner.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return dados;
  }

  private String[] splitLine(String line) {
    return line.split(";");
  }

  private boolean compararString(String s1, String s2) {
    return s1.equals(s2);
  }
}
