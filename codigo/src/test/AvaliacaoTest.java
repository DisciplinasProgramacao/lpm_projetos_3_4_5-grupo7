package test;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import src.Avaliacao;

public class AvaliacaoTest {
    private Avaliacao avaliacao;
    // @Test()
    // public void testaCadastrarAvaliacao() {
    //     avaliacao = new Avaliacao();
    //     avaliacao.cadastrarAvaliacao(1);
    //     avaliacao.cadastrarAvaliacao(5);
    //     avaliacao.cadastrarAvaliacao(3);
    //     Assertions.assertEquals(3, avaliacao.getAvaliacoes().size());
    // }

    // @Test()
    // public void testaGerarMedia() {
    //     avaliacao = new Avaliacao();
    //     avaliacao.cadastrarAvaliacao(5);
    //     avaliacao.cadastrarAvaliacao(5);
    //     avaliacao.cadastrarAvaliacao(2);
    //     Assertions.assertEquals(4, avaliacao.gerarMedia());
    // }

    @Test()
    public void testaComentario() {
        avaliacao = new Avaliacao(5.0, "Muito bom");
        Assertions.assertEquals(avaliacao.getComentario(), "Muito bom");
    }

    @Test()
    public void testaNota() {
        avaliacao = new Avaliacao(4.3, "Muito bom");
        Assertions.assertEquals(avaliacao.getNota(), 4.3);
    }
}