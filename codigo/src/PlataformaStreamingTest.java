package src;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

public class PlataformaStreamingTest {
  PlataformaStreaming ps;

  /**
   * Este teste é executado antes de todos os outros, ele cria uma
   * instância da classe PlataformaStreaming e adiciona um cliente na mesma.
   * */
  @BeforeEach
  public void adicionaCliente() {
    ps = new PlataformaStreaming();
    ps.adicionarCliente(new Cliente("user", "senha"));
  }

  /**
   * Este teste verifica se existe um usuário com o usuário e senha passados por parâmetro,
   * se existe, ele efetua o login na plataforma.
   * */
  @Test
  public void testaLogin() {
    ps = new PlataformaStreaming();
    ps.adicionarCliente(new Cliente("user", "senha"));
    Cliente c = ps.login("user", "senha");

    Assertions.assertTrue(c instanceof Cliente);
  }

  /**
   * Este teste adiciona uma série na plataforma e verifica se não houve erros retornados.
   * */
  @Test
  public void testaAdicionarSerie() {
    ps = new PlataformaStreaming();
    Assertions.assertDoesNotThrow(() -> ps.adicionarSerie(new Serie(1, "serie", "01/01/2021")));
  }

  /**
   * Este teste adiciona um cliente na plataforma e verifica se não houve erros retornados.
   * */
  @Test
  public void testaAdicionarCliente() {
    ps = new PlataformaStreaming();
    Assertions.assertDoesNotThrow(() -> ps.adicionarCliente(new Cliente("usuario", "senha")));
  }

  /**
   * Este teste adiciona séries na plataforma e filtra por gênero, ao filtrar, verifica se o tamanho da lista
   * é de acordo com a quantidade de séries de determinado gênero.
   * */
  @Test
  public void testaFiltrarPorGenero() {
    ps = new PlataformaStreaming();
    Serie serie = new Serie(1, "shrek serie", "01/01/2021");
    Serie serie2 = new Serie(2, "shrek serie 2", "01/01/2021");
    Serie serie3 = new Serie(3, "shrek serie 3", "01/01/2021");
    Serie serie4 = new Serie(4, "shrek serie 4", "01/01/2021");
    List<Audiovisual> filtradas;

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

  /**
   * Este teste adiciona séries na plataforma e filtra por idioma, ao filtrar, verifica se o tamanho da lista
   * é de acordo com a quantidade de séries de determinado idioma.
   * */
  @Test
  public void testaFiltrarIdioma() {
    ps = new PlataformaStreaming();
    Serie serie = new Serie(1, "serie", "01/01/2021");
    Serie serie2 = new Serie(2, "shrek 3", "01/01/2021");
    Serie serie3 = new Serie(3, "shrek 2", "01/01/2021");
    List<Audiovisual> filtradas = new ArrayList<>();

    serie.setIdioma("Português");
    serie2.setIdioma("Inglês");
    serie3.setIdioma("Espanhol");

    ps.adicionarSerie(serie);
    ps.adicionarSerie(serie2);
    ps.adicionarSerie(serie3);

    filtradas = ps.filtrarPorIdioma("Português");
    Assertions.assertEquals(1, filtradas.size());
  }

  /**
   * Este teste adiciona séries na plataforma e filtra por quantidade de episódios, ao filtrar, verifica se o tamanho da lista
   * é de acordo com a quantidade de séries de determinada quantia de episódios.
   * */
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

  /**
   * Este teste registra audiências e vefirica se a quantidade de audiências registradas
   * é igual a um determinado valor.
   * */
  @Test
  public void testaRegistrarAudiencia() {
    Serie serie = new Serie(1, "serie", "01/01/2021");
    serie.registrarAudiencia();
    serie.registrarAudiencia();
    serie.registrarAudiencia();

    Assertions.assertEquals(3, serie.getAudiencia());
  }
}
