package test;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import src.Serie;
import src.Cliente;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

public class SerieTest {
  @Test
  public void testaRegistraAudiencia() {
    Serie serie = new Serie(123, "serie", "01/01/2021");

    serie.registrarAudiencia();
    serie.registrarAudiencia();
    serie.registrarAudiencia();

    Assertions.assertEquals(3, serie.getAudiencia());
  }

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
}