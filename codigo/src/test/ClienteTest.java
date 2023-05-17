package test;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import src.Cliente;
import src.Serie;

public class ClienteTest {
    @Test() 
    public void testaEspecialista() {
        Cliente c = new Cliente("diogo", "123");
        c.adicionarNaListaJaVistas(new Serie(1, "a", "b"));
        c.adicionarNaListaJaVistas(new Serie(2, "a", "b"));
        c.adicionarNaListaJaVistas(new Serie(3, "a", "b"));
        c.adicionarNaListaJaVistas(new Serie(4, "a", "b"));
        c.adicionarNaListaJaVistas(new Serie(5, "a", "b"));
        c.adicionarNaListaJaVistas(new Serie(6, "a", "b"));

        Assertions.assertEquals(true, c.getEspecialista());
    }
}