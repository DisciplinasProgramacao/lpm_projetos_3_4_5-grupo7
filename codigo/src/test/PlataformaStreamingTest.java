package test;

import org.junit.*;
import org.junit.jupiter.api.Assertions;
import src.Cliente;
import src.Generos;
import src.Idiomas;
import src.PlataformaStreaming;
import src.Serie;

public class PlataformaStreamingTest {
  PlataformaStreaming ps;

  @Test
  public void testaLogin() {
    ps = new PlataformaStreaming();
    ps.cadastro("joaozinho", "user", "senha", "Comum");
    Cliente c = ps.login("user", "senha");
    Assertions.assertNotNull(c);
  }

  @Test
  public void testaAdicionarSerie() {
    ps = new PlataformaStreaming();
    Assertions.assertDoesNotThrow(() -> ps.adicionarSerie(new Serie(1, "serie", "01/01/2021")));
  }

  @Test
  public void testaAdicionarCliente() {
    ps = new PlataformaStreaming();
    Assertions.assertDoesNotThrow(() -> ps.cadastro("joaozinho", "user", "senha", "Comum"));
  }

  @Test
  public void testeEnumGeneros() {
    Assertions.assertEquals("ROMANCE", Generos.ROMANCE.toString());
  }

  @Test
  public void testaFiltrarIdioma() {
    Assertions.assertEquals("PORTUGUES", Idiomas.PORTUGUES.toString());
  }

}
