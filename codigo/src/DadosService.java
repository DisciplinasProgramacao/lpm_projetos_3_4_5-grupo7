package src;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DadosService {
  private final PlataformaStreaming plataformaStreaming;

  public DadosService(PlataformaStreaming ps) {
    plataformaStreaming = ps;
  }

  public void carregarClientes() {
    List<String> clientes = criarArrayDeClientes();

    clientes.forEach(clienteLine -> {
      String[] dados = splitLine(clienteLine, ";");
      String login = formatLine(dados[1]);
      String senha = formatLine(dados[2]);
      Cliente cliente = new Cliente(login, senha);

      buscarAudiencia(cliente);
      plataformaStreaming.adicionarCliente(cliente);
    });
  }

  public void carregarSeries() {
    List<String> series = criarArrayDeSeries();

    series.forEach(serieLine -> {
      String[] dados = splitLine(serieLine, ";");

      int id = Integer.parseInt(formatLine(dados[0]));
      String nome = formatLine(dados[1]);
      String dataLancamento = formatLine(dados[2]);

      plataformaStreaming.adicionarSerie(new Serie(id, nome, dataLancamento));
    });
  }

  public void carregarFilmes() {
    List<String> filmes = criarArrayDeFilmes();

    filmes.forEach(filmeLine -> {
      String[] dados = splitLine(filmeLine, ";");

      int id = Integer.parseInt(formatLine(dados[0]));
      String nome = formatLine(dados[1]);
      String dataLancamento = formatLine(dados[2]);
      int duracao = Integer.parseInt(formatLine(dados[3]));

      plataformaStreaming.adicionarFilme(new Filme(id, nome, dataLancamento, duracao));
    });

  }

  private void buscarAudiencia(Cliente cliente) {
    List<String> audiencias = criarArrayDeAudiencias();
    List<String> series = criarArrayDeSeries();

    audiencias.forEach(audienciaLine -> {
      String[] dadosAudiencia = splitLine(audienciaLine, ";");
      String loginClienteParaBuscar = formatLine(dadosAudiencia[0]);
      String serieParaAssistir = formatLine(dadosAudiencia[1]);
      String idSerieParaBuscar = formatLine(dadosAudiencia[2]);

      if (compararString(loginClienteParaBuscar, cliente.getNomeUsuario())) {
        series.forEach(serieLine -> {
          String[] dadosSerie = splitLine(serieLine, ";");
          String idSerie = formatLine(dadosSerie[0]);
          String nome = formatLine(dadosSerie[1]);

          if (compararString(idSerieParaBuscar, idSerie)) {
            Audiovisual serie = plataformaStreaming.buscarAudiovisual(nome);

            if (compararString(serieParaAssistir, "F")) {
              cliente.adicionarNaLista(serie);
            } else {
              cliente.adicionarNaListaJaVistas(serie);
            }
          }
        });
      }
    });
  }

  private List<String> criarArrayDeClientes() {
    String arquivoCliente = "codigo/src/files/POO_Espectadores.csv";
    return carregarDados(arquivoCliente);
  }

  private List<String> criarArrayDeSeries() {
    String nomeArquivo = "codigo/src/files/POO_Series.csv";
    return carregarDados(nomeArquivo);
  }

  private List<String> criarArrayDeFilmes() {
    String nomeArquivo = "codigo/src/files/POO_Filmes.csv";
    return carregarDados(nomeArquivo);
  }

  private List<String> criarArrayDeAudiencias() {
    String nomeArquivo = "codigo/src/files/POO_Audiencia.csv";
    return carregarDados(nomeArquivo);
  }

  private List<String> carregarDados(String nomeArquivo) {
    List<String> dados = new ArrayList<>();

    try {

      Scanner scanner = new Scanner(new File(nomeArquivo));
      Scanner aux = scanner;

      if (aux.hasNextLine() && aux.nextLine().contains("#"))
        scanner.nextLine();

      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        System.out.println("[" + line + "]\n");
        dados.add(line);

      }

      scanner.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return dados;
  }

  private String[] splitLine(String line, String separador) {
    return line.split(separador);
  }

  private String formatLine(String line) {
    return line.trim();
  }

  private boolean compararString(String s1, String s2) {
    return s1.equals(s2);
  }
}