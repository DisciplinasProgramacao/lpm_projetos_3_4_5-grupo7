package test;

import java.util.List;
import java.util.function.Predicate;

import org.junit.*;
import org.junit.jupiter.api.Assertions;
import src.Audiovisual;
import src.Cliente;
import src.Filtro;
import src.FiltroPersonalizado;
import src.PlataformaStreaming;
import src.Serie;

public class PlataformaStreamingTest {
  PlataformaStreaming ps;

  @Test
  public void testaCadastro() {
    ps = new PlataformaStreaming();
    ps.cadastro("joão kleber", "jk123", "12345", "Profissional");
    Cliente c = ps.login("jk123", "12345");
    System.out.println(c.getTipo());
    Assertions.assertNotNull(c);
  }

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
    Assertions.assertEquals("ROMANCE", Audiovisual.Generos.values()[2].toString());
  }

  @Test
  public void testaFiltrarIdioma() {
    Assertions.assertEquals("PORTUGUES", Audiovisual.Idiomas.values()[0].toString());
  }

  @Test
  public void testaFiltrarPorQtdEpisodios() {
    ps = new PlataformaStreaming();
    Serie serie1 = new Serie(1, "shrek serie", "01/01/2021");
    Serie serie2 = new Serie(2, "shrek serie 2", "01/01/2021");
    Serie serie3 = new Serie(3, "shrek serie 3", "01/01/2021");

    serie1.setQuantidadeEpisodios(5);
    serie2.setQuantidadeEpisodios(10);
    serie3.setQuantidadeEpisodios(5);

    ps.adicionarSerie(serie1);
    ps.adicionarSerie(serie2);
    ps.adicionarSerie(serie3);

    Filtro<Serie> f = new Filtro<>();

    Predicate<FiltroPersonalizado<Serie>> filtrador = filtro -> Integer
        .toString(filtro.getElemento().getQuantidadeEpisodios())
        .equals(filtro.getBusca());

    String qtdEpisodios = "5";

    List<Serie> palavrasFiltradas = f.filtrar(ps.getListaSerie(), filtrador, qtdEpisodios);

    if (palavrasFiltradas.size() > 0)
      palavrasFiltradas.forEach(serie -> System.out.println(serie.toString()));
    Assertions.assertEquals(2, palavrasFiltradas.size());
  }

  @Test
  public void testaRegistrarAudiencia() {
    Serie serie = new Serie(1, "serie", "01/01/2021");
    serie.registrarAudiencia();
    serie.registrarAudiencia();
    serie.registrarAudiencia();

    Assertions.assertEquals(3, serie.getAudiencia());
  }
}
