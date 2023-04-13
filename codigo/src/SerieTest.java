package src;

import org.junit.*;
import org.junit.jupiter.api.Assertions;

public class SerieTest {
  @Test
  public void testaRegistraAudiencia() {
    Serie serie = new Serie();

    serie.registrarAudiencia();
    serie.registrarAudiencia();
    serie.registrarAudiencia();

    Assertions.assertEquals(3, serie.getRegistrarAudiencia());
  }
}
