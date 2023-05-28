package test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

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

        Assertions.assertTrue(c.getEspecialista());
    }

    @Test()
    public void testaClienteRegular() {
        Cliente c = new Cliente("bruno", "123");
        Assertions.assertFalse(c.getEspecialista());
    }

    @Test()
    public void avaliacaoClienteRegular() {
        Cliente c = new Cliente("bruno", "123");
        Serie s = new Serie(1, "a", "b");
        c.adicionarAvaliacao(s, 5);
        Assertions.assertEquals("Nota: 5.0 - Comentário: null", s.mostrarAvaliacoes());
    }

    @Test()
    public void avaliacaoClienteEspecialista() {
        Cliente c = new Cliente("joao caram", "123");
        c.adicionarNaListaJaVistas(new Serie(1, "a", "b"));
        c.adicionarNaListaJaVistas(new Serie(2, "a", "b"));
        c.adicionarNaListaJaVistas(new Serie(3, "a", "b"));
        c.adicionarNaListaJaVistas(new Serie(4, "a", "b"));
        c.adicionarNaListaJaVistas(new Serie(5, "a", "b"));

        Serie s = new Serie(6, "a", "b");

        Assertions.assertTrue(c.adicionarAvaliacao(s, 5, "bem divertido!"));

    }

    @Test()
    public void avaliacaoClienteNaoEspecialista() {
        Cliente c = new Cliente("pedro bial", "123");
        Serie s = new Serie(6, "a", "b");
        assertEquals(false, c.adicionarAvaliacao(s, 5, "bem divertido!"));
        Assertions.assertFalse(c.adicionarAvaliacao(s, 5, "nao sou especialista mas quero por comentario"));
    }

}