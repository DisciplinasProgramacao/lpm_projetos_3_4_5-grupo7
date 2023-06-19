package test;

import org.junit.jupiter.api.*;

import src.Serie;
import src.Tipo;
import src.Cliente;
import src.EnumTipoCliente;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.time.LocalDate;

public class SerieTest {

  @Test
  public void registraDataAssistido() {
    Serie serie = new Serie(123, "serie", "01/02/2003");

    serie.setDataAssistido();

    Assertions.assertEquals(serie.getDataAssistido(), LocalDate.now());
  }

  @Test
  public void testaMediaDeAvaliacoes() {
    Cliente cliente1 = new Cliente("123", "123");
    Cliente cliente2 = new Cliente("321", "321");
    Cliente cliente3 = new Cliente("456", "456");

    Serie serie = new Serie(123, "serie", "01/02/2003");

    try {
      cliente1.adicionarAvaliacao(serie, 2, "");
      cliente2.adicionarAvaliacao(serie, 5, "");
      cliente3.adicionarAvaliacao(serie, 2, "");
    } catch (Exception e) {
    }

    assertEquals(3.0, serie.gerarMediaAvaliacoes(), 0.1);
  }

  @Test
  public void testeMediaAvaliacoesVazia() {
    Serie serie = new Serie(123, "serie", "01/02/2003");

    assertEquals(0.0, serie.gerarMediaAvaliacoes(), 0.1);
  }

  @Test
  public void testeMidiaRestritaException() {
    Serie serie = new Serie(123, "serie", "01/02/2003", Tipo.PRELANCAMENTO);

    Cliente cliente1 = new Cliente("123", "123");

    assertThrows(IllegalArgumentException.class, () -> cliente1.adicionarNaListaJaVistas(serie));
  }

  @Test
  public void testeMidiaRestritaClienteProf() {
    Serie serie = new Serie(123, "serie", "01/02/2003", Tipo.PRELANCAMENTO);

    Cliente cliente1 = new Cliente("123", "123", "123", EnumTipoCliente.PROFISSIONAL);

    assertDoesNotThrow(() -> cliente1.adicionarNaListaJaVistas(serie));
  }
}
