//package test;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.BeforeEach;
//import src.*;
//
//import java.util.Comparator;
//
//public class RelatorioTest {
//    private Relatorio relatorio;
//    private PlataformaStreaming ps;
//    @BeforeEach
//    public void init() {
//        ps = new PlataformaStreaming();
//        relatorio = new Relatorio(ps.getClientes(), ps.getHashMapAudioVisual());
//    }
//
//    @Test
//    public void testaGerarRelatorioMidiasAssistidas() {
//        Filme filme1 = new Filme();
//        Filme filme2 = new Filme();
//        Filme filme3 = new Filme();
//        Serie serie1 = new Serie();
//        Serie serie2 = new Serie();
//        Serie serie3 = new Serie();
//
//        ps.adicionarFilme(filme1);
//        ps.adicionarFilme(filme2);
//        ps.adicionarFilme(filme3);
//        ps.adicionarSerie(serie1);
//        ps.adicionarSerie(serie2);
//        ps.adicionarSerie(serie3);
//
//        ps.adici("bruno", "bruno", "123123", "Comum");
//        ps.cadastro("samuel", "samuel", "123123", "Comum");
//        ps.cadastro("marcos", "marcos", "123123", "Comum");
//        ps.cadastro("eduardo", "eduardo", "123123", "Comum");
//
//        Cliente cliente =
//
//
//
//        Comparator<Cliente> comparatorCliente = Comparator.comparingInt(c -> c.getAssistidas().size());
//        Cliente cliente = relatorio.gerar(comparatorCliente);
//
//        Assertions.assertEquals("marcos",cliente.getLogin());
//    }
//}
