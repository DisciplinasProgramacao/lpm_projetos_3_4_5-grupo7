package src;

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

  }

  @Test
  public void testaAdicionarCliente() {

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
