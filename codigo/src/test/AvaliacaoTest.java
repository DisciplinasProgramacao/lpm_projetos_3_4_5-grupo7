package test;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import src.Avaliacao;

public class AvaliacaoTest {
    private Avaliacao avaliacao;
    @Test()
    public void testaCadastrarAvaliacao() {
        avaliacao = new Avaliacao();
        avaliacao.cadastrarAvaliacao(1);
        avaliacao.cadastrarAvaliacao(5);
        avaliacao.cadastrarAvaliacao(3);
        Assertions.assertEquals(3, avaliacao.getAvaliacoes().size());
    }

    @Test()
    public void testaGerarMedia() {
        avaliacao = new Avaliacao();
        avaliacao.cadastrarAvaliacao(5);
        avaliacao.cadastrarAvaliacao(5);
        avaliacao.cadastrarAvaliacao(2);
        Assertions.assertEquals(4, avaliacao.gerarMedia());
    }
}