package test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.junit.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import src.Audiovisual;
import src.Cliente;
import src.Filtro;
import src.FiltroPersonalizado;
import src.PlataformaStreaming;
import src.Serie;

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
    Assertions.assertDoesNotThrow(() -> ps.adicionarSerie(new Serie(1, "serie", "01/01/2021")));
  }

  @Test
  public void testaAdicionarCliente() {
    ps = new PlataformaStreaming();
    Assertions.assertDoesNotThrow(() -> ps.adicionarCliente(new Cliente("usuario", "senha")));
  }

  @Test
  public void testaFiltrarPorGenero() {
    ps = new PlataformaStreaming();
    Serie serie = new Serie(1, "shrek serie", "01/01/2021");
    Serie serie2 = new Serie(2, "shrek serie 2", "01/01/2021");
    Serie serie3 = new Serie(3, "shrek serie 3", "01/01/2021");
    Serie serie4 = new Serie(4, "shrek serie 4", "01/01/2021");

    serie.setGenero("Comédia");
    serie2.setGenero("Ação");
    serie3.setGenero("Drama");
    serie4.setGenero("Comédia");

    ps.adicionarSerie(serie);
    ps.adicionarSerie(serie2);
    ps.adicionarSerie(serie3);
    ps.adicionarSerie(serie4);

    Filtro<Audiovisual> f = new Filtro<>();

    Predicate<FiltroPersonalizado<Audiovisual>> filtrador = filtro -> filtro.getElemento().getGenero()
            .equals(filtro.getBusca());

    String genero = "Comédia";

    List<Audiovisual> palavrasFiltradas = f.filtrar(ps.getListaAudioVisual(), filtrador, genero);

    palavrasFiltradas.forEach(audiovisual -> System.out.println(audiovisual.getNome()));
    Assertions.assertEquals(2, palavrasFiltradas.size());
  }

  @Test
  public void testaFiltrarIdioma() {
    ps = new PlataformaStreaming();
    Serie serie = new Serie(1, "serie", "01/01/2021");
    Serie serie2 = new Serie(2, "shrek 3", "01/01/2021");
    Serie serie3 = new Serie(3, "shrek 2", "01/01/2021");
    
    serie.setIdioma("Português");
    serie2.setIdioma("Inglês");
    serie3.setIdioma("Espanhol");

    ps.adicionarSerie(serie);
    ps.adicionarSerie(serie2);
    ps.adicionarSerie(serie3);

    Filtro<Audiovisual> f = new Filtro<>();

    Predicate<FiltroPersonalizado<Audiovisual>> filtrador = filtro -> filtro.getElemento().getIdioma()
            .equals(filtro.getBusca());

    String idioma = "Português";

    List<Audiovisual> palavrasFiltradas = f.filtrar(ps.getListaAudioVisual(), filtrador, idioma);

    palavrasFiltradas.forEach(audiovisual -> System.out.println(audiovisual.getNome()));


    Assertions.assertEquals(1, palavrasFiltradas.size());
  }

  @Test
  public void testaFiltrarPorQtdEpisodios() {
    ps = new PlataformaStreaming();
    Serie serie = new Serie(1, "shrek serie", "01/01/2021");
    Serie serie2 = new Serie(2, "shrek serie 2", "01/01/2021");
    Serie serie3 = new Serie(3, "shrek serie 3", "01/01/2021");
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
    Serie serie = new Serie(1, "serie", "01/01/2021");
    serie.registrarAudiencia();
    serie.registrarAudiencia();
    serie.registrarAudiencia();

    Assertions.assertEquals(3, serie.getAudiencia());
  }
}
