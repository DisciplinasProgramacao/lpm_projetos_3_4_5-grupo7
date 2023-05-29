package src;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class DadosService {
  private final PlataformaStreaming plataformaStreaming;

  public DadosService(PlataformaStreaming ps) {
    plataformaStreaming = ps;
  }

  /**
   * Método para carregar clientes a partir do arquivo de clientes
   */
  public void carregarClientes() {
    List<String> clientes = criarArrayDeClientes();

    clientes.forEach(clienteLine -> {
      String[] dados = clienteLine.split(";");
      String login = dados[1].trim();
      String senha = dados[2].trim();
      Cliente cliente = new Cliente(login, senha);

      buscarAudiencia(cliente);
      plataformaStreaming.cadastro(login, login, senha);
    });
  }

  /**
   * Método para carregar séries a partir do arquivo de séries
   */
  public void carregarSeries() {
    List<String> series = criarArrayDeSeries();

    series.forEach(serieLine -> {
      String[] dados = serieLine.split(";");

      int id = Integer.parseInt(dados[0].trim());
      String nome = dados[1].trim();
      String dataLancamento = dados[2].trim();

      plataformaStreaming.adicionarSerie(new Serie(id, nome, dataLancamento));
    });
  }

  /**
   * Método para carregar filmes a partir do arquivo de filmes
   */
  public void carregarFilmes() {
    List<String> filmes = criarArrayDeFilmes();

    filmes.forEach(filmeLine -> {
      String[] dados = filmeLine.split(";");

      int id = Integer.parseInt(dados[0].trim());
      String nome = dados[1].trim();
      String dataLancamento = dados[2].trim();
      int duracao = Integer.parseInt(dados[3].trim());

      plataformaStreaming.adicionarFilme(new Filme(id, nome, dataLancamento, duracao));
    });

  }

  /**
   * Método que coloca audiência na lista de Para Ver ou de Assistidos do cliente
   * 
   * @param cliente
   */
  private void buscarAudiencia(Cliente cliente) {
    List<String> audiencias = criarArrayDeAudiencias();
    List<String> series = criarArrayDeSeries();

    audiencias.forEach(audienciaLine -> {
      String[] dadosAudiencia = audienciaLine.split(";");
      String loginClienteParaBuscar = dadosAudiencia[0].trim();
      String serieParaAssistir = dadosAudiencia[1].trim();
      String idSerieParaBuscar = dadosAudiencia[2].trim();

      if (loginClienteParaBuscar.equals(cliente.getNomeUsuario())) {
        series.forEach(serieLine -> {
          String[] dadosSerie = serieLine.split(";");
          String idSerie = dadosSerie[0].trim();
          String nome = dadosSerie[1].trim();

          if (idSerieParaBuscar.equals(idSerie)) {
            Audiovisual serie = plataformaStreaming.buscarAudiovisual(nome);

            if (serieParaAssistir.equals("F")) {
              cliente.adicionarNaLista(serie);
            } else {
              cliente.adicionarNaListaJaVistas(serie);
            }
          }
        });
      }
    });
  }

  /**
   * Método que lê o arquivo de Clientes e retorna uma Lista a partir do método de
   * carregarDados
   * 
   * @return List<String>
   */
  private List<String> criarArrayDeClientes() {
    String arquivoCliente = "codigo/src/files/POO_Espectadores.csv";
    return carregarDados(arquivoCliente);
  }

  /**
   * Método que lê o arquivo de Séries e retorna uma Lista a partir do método de
   * carregarDados
   * 
   * @return List<String>
   */
  private List<String> criarArrayDeSeries() {
    String nomeArquivo = "codigo/src/files/POO_Series.csv";
    return carregarDados(nomeArquivo);
  }

  /**
   * Método que lê o arquivo de Filmes e retorna uma Lista a partir do método de
   * carregarDados
   * 
   * @return List<String>
   */
  private List<String> criarArrayDeFilmes() {
    String nomeArquivo = "codigo/src/files/POO_Filmes.csv";
    return carregarDados(nomeArquivo);
  }

  /**
   * Método que lê o arquivo de Audiências e retorna uma Lista a partir do método
   * de carregarDados
   * 
   * @return List<String>
   */
  private List<String> criarArrayDeAudiencias() {
    String nomeArquivo = "codigo/src/files/POO_Audiencia.csv";
    return carregarDados(nomeArquivo);
  }

  /**
   * Método que carrega dados a partir de um arquivo e retorna uma lista de
   * Strings
   * 
   * @param nomeArquivo
   * @return List<String>
   */
  private List<String> carregarDados(String nomeArquivo) {
    List<String> dados = new Stack<String>();

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

}