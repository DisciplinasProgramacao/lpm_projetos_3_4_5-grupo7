package src;

import java.util.*;

import static java.util.stream.Collectors.joining;

public class Relatorio {
    private final HashMap<String, Cliente> clientes;
    private final HashMap<Integer, Audiovisual> audiovisuais;

    /**
     * Construtor da classe Relatorio, que é inicializado com HashMap de Cliente e de Audiovisual
     *
     * @param cliente HashMap<String, Cliente>
     * @param audiovisuais HashMap<Integer, Audiovisual>
     * */
    public Relatorio(HashMap<String, Cliente> cliente, HashMap<Integer, Audiovisual> audiovisuais) {
        this.clientes = cliente;
        this.audiovisuais = audiovisuais;
    }

    /**
     * Gera relatório de cliente que teve mais mídias assistidas
     *
     * @param comparator Comparator<Cliente>
     * @return Relatorio com o cliente que teve mais mídias assistidas
     * */
    public String gerarRelatorioDeMidia(Comparator<Cliente> comparator) {
        Cliente cliente = Collections.max(clientes.values(), comparator);
        return "O cliente que assistiu mais mídias foi o " + cliente.getLogin() + ", com "
                + cliente.getAssistidas().size() + " assistidos";
    }

    /**
     * Gera relatório de cliente que teve mais mídias avaliadas
     *
     * @param comparator Comparator<Cliente>
     * @return Relatorio com o cliente que teve mais mídias avaliadas
     * */
    public String gerarRelatorioAvaliacao(Comparator<Cliente> comparator) {
        Cliente cliente = Collections.max(clientes.values(), comparator);
        return "O cliente que avaliou mais mídias foi o " + cliente.getLogin() + ", com "
                + cliente.getAvaliacoes().size() + " avalidadas";
    }

    // TODO - corrigir: gerando null pointer exception
    public String gerarRelatorioClientes15Avaliacoes() {
        List<Cliente> clientes15Avaliacoes = clientes.values().stream()
                .filter(cliente -> cliente.getAvaliacoes().size() >= 15).toList();

        double porcentagem = (double) clientes15Avaliacoes.size() / clientes.size() * 100;
        return "A porcentagem de clientes que avaliaram mais de 15 mídias é de " + porcentagem + "%";
    }

    // TODO - corrigir: não está imprimindo os audiovisuais
    public String gerarRelatorioDezMelhores() {
        // Quais são as 10 mídias de melhor avaliação, com pelo menos 100 avaliações, em
        // ordem decrescente;

        String lista10Audiovisuais = this.audiovisuais.values().stream()
                .filter(audiovisual -> audiovisual.getAvaliacoes().size() >= 100)
                .sorted(Comparator.comparingDouble(Audiovisual::gerarMediaAvaliacoes).reversed()).limit(10)
                .map(Audiovisual::toString).collect(joining(", "));

        return "As 10 midias de melhor avaliacao são: " + lista10Audiovisuais;
    }

    public String gerarRelatorioMaisVistas() {
        return null;
    }

    public String gerarRelatorioDezMelhoresGenero() {
        return null;
    }

    public String gerarRelatorioMaisVistasGenero() {
        return null;
    }
}
