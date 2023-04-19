package src;

import java.io.File;
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

  public List<Cliente> geraVetorCliente(String nomeArquivo) {
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
        System.out.println(line);
      }

      scanner.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return todosOsClientes;
  }


  public List<Serie> geraVetorSerie(String nomeArquivo) {
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
        System.out.println(line);
      }

      scanner.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return todasAsSeries;
  }
}
