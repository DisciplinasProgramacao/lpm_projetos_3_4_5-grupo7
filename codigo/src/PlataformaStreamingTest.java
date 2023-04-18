package src;

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

  }

  @Test
  public void testaFiltrarIdioma() {

  }

  @Test
  public void testaFiltrarPorQtdEpisodios() {

  }

  @Test
  public void testaRegistrarAudiencia() {

  }
}
