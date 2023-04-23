
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
    ps.adicionarCliente(new Cliente("user", "senha"));
  }

  @Test
  public void testaLogin() {
    ps = new PlataformaStreaming();
    ps.adicionarCliente(new Cliente("user", "senha"));
    Cliente c = ps.login("user", "senha");

    Assertions.assertTrue(c instanceof Cliente);
  }

  @Test
  public void testaAdicionarSerie() {
    ps = new PlataformaStreaming();
    Assertions.assertDoesNotThrow(() -> ps.adicionarSerie(new Serie("serie")));
  }

  @Test
  public void testaAdicionarCliente() {
    ps = new PlataformaStreaming();
    Assertions.assertDoesNotThrow(() -> ps.adicionarCliente(new Cliente("usuario", "senha")));
  }

  @Test
  public void testaFiltrarPorGenero() {
    ps = new PlataformaStreaming();
    Serie serie = new Serie("shrek serie");
    Serie serie2 = new Serie("shrek serie 2");
    Serie serie3 = new Serie("shrek serie 3");
    Serie serie4 = new Serie("shrek serie 4");
    List<Serie> filtradas = new ArrayList<>();

    serie.setGenero("Comédia");
    serie2.setGenero("Ação");
    serie3.setGenero("Drama");
    serie4.setGenero("Comédia");

    ps.adicionarSerie(serie);
    ps.adicionarSerie(serie2);
    ps.adicionarSerie(serie3);
    ps.adicionarSerie(serie4);

    filtradas = ps.filtrarPorGenero("Comédia");
    Assertions.assertEquals(2, filtradas.size());
  }

  @Test
  public void testaFiltrarIdioma() {
    ps = new PlataformaStreaming();
    Serie serie = new Serie("serie");
    Serie serie2 = new Serie("shrek 3");
    Serie serie3 = new Serie("shrek 2");
    List<Serie> filtradas = new ArrayList<>();

    serie.setIdioma("Português");
    serie2.setIdioma("Inglês");
    serie3.setIdioma("Espanhol");

    ps.adicionarSerie(serie);
    ps.adicionarSerie(serie2);
    ps.adicionarSerie(serie3);

    filtradas = ps.filtrarPorIdioma("Português");
    Assertions.assertEquals(1, filtradas.size());
  }

  @Test
  public void testaFiltrarPorQtdEpisodios() {
    ps = new PlataformaStreaming();
    Serie serie = new Serie("shrek serie");
    Serie serie2 = new Serie("shrek serie 2");
    Serie serie3 = new Serie("shrek serie 3");
    List<Serie> filtradas = new ArrayList<>();

    serie.setQuantidadeEpisodios(5);
    serie2.setQuantidadeEpisodios(10);
    serie3.setQuantidadeEpisodios(5);

    ps.adicionarSerie(serie);
    ps.adicionarSerie(serie2);
    ps.adicionarSerie(serie3);

    filtradas = ps.filtrarPorQtdEpisodios(5);
    Assertions.assertEquals(2, filtradas.size());
  }

  @Test
  public void testaRegistrarAudiencia() {
    Serie serie = new Serie("serie");
    serie.registrarAudiencia();
    serie.registrarAudiencia();
    serie.registrarAudiencia();

    Assertions.assertEquals(3, serie.getAudiencia());
  }
}
