package src;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

public class PlataformaStreamingTest {
  PlataformaStreaming ps;

  @BeforeEach
  public void adicionaCliente() {
    ps = new PlataformaStreaming();
    ps.adicionarCliente(new Cliente());
  }

  @Test
  public void testaLogin() {
    PlataformaStreaming ps = new PlataformaStreaming();
    Cliente c = ps.login("usuarioteste", "senhaforte");

    Assertions.assertTrue(c instanceof Cliente);
  }

  @Test
  public void testaAdicionarSerie() {
    PlataformaStreaming ps = new PlataformaStreaming();
    Boolean adicionou = ps.adicionarSerie(new Serie());
    Assertions.assertTrue(adicionou);
  }

  @Test
  public void testaAdicionarCliente() {
    PlataformaStreaming ps = new PlataformaStreaming();
    Boolean adicionou = ps.adicionarCliente(new Cliente());
    Assertions.assertTrue(adicionou);
  }

  @Test
  public void testaFiltrarPorGenero() {
    List<Serie> filtradas = new ArrayList<>();
    String genero = "Ação";

    filtradas = ps.filtrarPorGenero(genero);
    Assertions.assertEquals(1, filtradas.size());
  }

  @Test
  public void testaFiltrarIdioma() {
    List<Serie> filtradas = new ArrayList<>();
    String idioma = "Português";

    filtradas = ps.filtrarPorIdioma(idioma);
    Assertions.assertEquals(1, filtradas.size());
  }

  @Test
  public void testaFiltrarPorQtdEpisodios() {
    List<Serie> filtradas = new ArrayList<>();
    int qtdEpisodios = 10;

    filtradas = ps.filtrarPorQtdEpisodios(qtdEpisodios);
    Assertions.assertEquals(1, filtradas.size());
  }

  @Test
  public void testaRegistrarAudiencia() {
    Serie serie = new Serie();
    serie.registrarAudiencia();
    serie.registrarAudiencia();
    serie.registrarAudiencia();

    Assertions.assertEquals(3, serie.getAudiencia());
  }
}
