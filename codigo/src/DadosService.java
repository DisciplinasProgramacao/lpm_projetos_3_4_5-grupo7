package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DadosService {
  private PlataformaStreaming ps;

  public DadosService() {
    this.ps = new PlataformaStreaming();
  }

  public void carregarDados(String nomeArquivo) {
    File arquivo;

    try {
      arquivo = new File(nomeArquivo);
      if (!arquivo.exists()) {
        return;
      }

      Scanner scanner = new Scanner(arquivo);

      if (!scanner.hasNext()) {
        scanner.close();
        return;
      }

      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String[] dadosEspectador = line.split(";");

        String nomeUsuario = dadosEspectador[1];
        String senha = dadosEspectador[2];

        Cliente cliente = new Cliente(nomeUsuario, senha);

        this.ps.adicionarCliente(cliente);
        System.out.println(line);
      }

      scanner.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public List<Cliente> geraListaCliente(String nomeArquivo) {
    File arquivo;
    List<Cliente> todosOsClientes = new ArrayList<>();

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
        String[] dadosCliente = line.split(";");

        String nomeUsuario = dadosCliente[1];
        String senha = dadosCliente[2];

        Cliente cliente = new Cliente(nomeUsuario, senha);

        this.ps.adicionarCliente(cliente);
      }

      scanner.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return todosOsClientes;
  }

  public List<Serie> geraListaSerie(String nomeArquivo) {
    File arquivo;
    List<Serie> todasAsSeries = new ArrayList<>();

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
        String[] dadosSerie = line.split(";");

        String nomeSerie = dadosSerie[1];

        Serie serie = new Serie(nomeSerie);

        this.ps.adicionarSerie(serie);
      }

      scanner.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return todasAsSeries;
  }

  public void geraListaAudiencia(String arquivoAudiencia) {

    File arquivo;

    try {
      arquivo = new File(arquivoAudiencia);
      if (!arquivo.exists()) {
        return;
      }

      Scanner scanner = new Scanner(arquivo);

      if (!scanner.hasNext()) {
        scanner.close();
        return;
      }

      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String[] dadosAudiencia = line.split(";");

        String nomeUsuario = dadosAudiencia[1];
        String lista = dadosAudiencia[2];
        String IdSerie = dadosAudiencia[3];

        Serie serie = new Serie(IdSerie);
        Cliente cliente = new Cliente(nomeUsuario, null);

        this.ps.adicionarAudiencia();
      }

      scanner.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
