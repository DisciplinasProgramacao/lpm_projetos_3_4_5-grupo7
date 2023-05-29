package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import src.Cliente;
import src.Serie;

public class ClienteTest {
    @Test()
    public void avaliacaoClienteRegular() {
        Cliente c = new Cliente("bruno", "123");
        Serie s = new Serie(1, "a", "b");

        try {
            c.adicionarAvaliacao(s, 5, null);
        } catch (Exception e) {
        }
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

        try {
            c.adicionarAvaliacao(s, 5, "bem divertido!");
        } catch (Exception e) {
        }

        Assertions.assertEquals("Nota: 5.0 - Comentário: bem divertido!", s.mostrarAvaliacoes());
    }

    @Test()
    public void ExcecaoDeClienteNaoEspecialista() {
        Cliente c = new Cliente("pedro bial", "123");
        Serie s = new Serie(6, "a", "b");

        try {
            c.adicionarAvaliacao(s, 5, "bem divertido!");
        } catch (Exception e) {
            assertEquals("Cliente não é especialista! Apenas a nota foi salva.", e.getMessage());
        }
    }

    @Test()
    public void avaliacaoDeClienteNaoEspecialista() {
        Cliente c = new Cliente("pedro bial", "123");
        Serie s = new Serie(6, "a", "b");

        try {
            c.adicionarAvaliacao(s, 5, "bem divertido!");
        } catch (Exception e) {
        }

        Assertions.assertEquals("Nota: 5.0 - Comentário: null", s.mostrarAvaliacoes());
    }

}