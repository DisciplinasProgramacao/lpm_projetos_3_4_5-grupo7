package src;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

public class SerieTest {
  @Test
  public void testaRegistraAudiencia() {
    Serie serie = new Serie("serie");

    serie.registrarAudiencia();
    serie.registrarAudiencia();
    serie.registrarAudiencia();

    Assertions.assertEquals(3, serie.getAudiencia());
  }
}
