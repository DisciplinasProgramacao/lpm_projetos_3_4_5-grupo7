package test;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import src.Serie;

public class SerieTest {
  @Test
  public void testaRegistraAudiencia() {
    Serie serie = new Serie(123, "serie", "01/01/2021");

    serie.registrarAudiencia();
    serie.registrarAudiencia();
    serie.registrarAudiencia();

    Assertions.assertEquals(3, serie.getAudiencia());
  }
}