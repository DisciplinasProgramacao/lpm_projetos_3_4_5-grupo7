package test;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import src.Audiovisual;
import src.Serie;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

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
  public void registraDataAssistido(){
    Serie serie = new Serie(123, "serie", "01/02/2003");

    serie.setDataAssistido();

    Assertions.assertEquals(serie.getDataAssistido(), LocalDate.now());
  }
}