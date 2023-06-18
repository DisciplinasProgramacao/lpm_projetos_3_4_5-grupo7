package src;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

public class Relatorio {
    private final HashMap<String, Cliente> clientes;
    private final HashMap<Integer, Audiovisual> audiovisuais;

    /**
     * Construtor da classe Relatorio, que é inicializado com HashMap de Cliente e
     * de Audiovisual
     *
     * @param cliente      HashMap<String, Cliente>
     * @param audiovisuais HashMap<Integer, Audiovisual>
     */
    public Relatorio(HashMap<String, Cliente> cliente, HashMap<Integer, Audiovisual> audiovisuais) {
        this.clientes = cliente;
        this.audiovisuais = audiovisuais;
    }

    /**
     * Gera relatório de cliente que teve mais mídias assistidas
     *
     * @param comparator Comparator<Cliente>
     * @return Relatorio com o cliente que teve mais mídias assistidas
     */
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
     */
    public String gerarRelatorioAvaliacao(Comparator<Cliente> comparator) {
        Cliente cliente = Collections.max(clientes.values(), comparator);
        return "O cliente que avaliou mais mídias foi o " + cliente.getLogin() + ", com "
                + cliente.getAvaliacoes().size() + " avalidadas";
    }

    // TODO - corrigir: gerando null pointer exception
    public String gerarRelatorioClientes15Avaliacoes() {
        List<Cliente> listaClientes = new ArrayList<>(clientes.values());

        // Calcular a quantidade de clientes com pelo menos 15 avaliações
        int clientesCom15Avaliacoes = 0;
        for (Cliente cliente : listaClientes) {
            if (cliente.getAvaliacoes().size() >= 15) {
                clientesCom15Avaliacoes++;
            }
        }

        // Calcular a porcentagem de clientes com pelo menos 15 avaliações
        double porcentagem = (double) clientesCom15Avaliacoes / listaClientes.size() * 100;

        return "A porcentagem de clientes com no mínimo 15 avaliações é de: " + porcentagem + "%";

    }

    // TODO - corrigir: não está imprimindo os audiovisuais
    public String gerarRelatorioDezMelhores() {
        // Quais são as 10 mídias de melhor avaliação, com pelo menos 100 avaliações, em
        // ordem decrescente;
        String lista10Audiovisuais = this.audiovisuais.values().stream()
                .filter(audiovisual -> audiovisual.getAvaliacoes().size() >= 100)
                .sorted(Comparator.comparingDouble(Audiovisual::gerarMediaAvaliacoes).reversed())
                .limit(10)
                .map(Audiovisual::toString)
                .collect(Collectors.joining(", "));

        return "As 10 mídias de melhor avaliação são: " + lista10Audiovisuais;
    }

    public String gerarRelatorio10MaisVistas() {
        // Quais são as 10 mídias mais vistas, em ordem decrescente;
        String lista10Audiovisuais = this.audiovisuais.values().stream()
                .sorted(Comparator.comparingInt(Audiovisual::getAudiencia).reversed())
                .limit(10)
                .map(Audiovisual::toString)
                .collect(Collectors.joining(", "));

        return "As 10 mídias mais vistas são: " + lista10Audiovisuais;
    }

    public String gerarRelatorioDezMelhoresGenero() {
        // Quais são as 10 mídias de melhor avaliação, com pelo menos 100 avaliações, em
        // ordem decrescente separadas por gênero;
        Map<String, String> melhoresPorGenero = this.audiovisuais.values().stream()
                .filter(audiovisual -> audiovisual.getAvaliacoes().size() >= 100)
                .sorted(Comparator.comparingDouble(Audiovisual::gerarMediaAvaliacoes).reversed())
                .limit(10)
                .collect(Collectors.groupingBy(Audiovisual::getGenero,
                        Collectors.mapping(Audiovisual::toString, Collectors.joining(", "))));

        StringBuilder relatorio = new StringBuilder("As 10 mídias de melhor avaliação por gênero são:\n");
        melhoresPorGenero.forEach(
                (genero, audiovisuais) -> relatorio.append(genero).append(": ").append(audiovisuais).append("\n"));

        return relatorio.toString();
    }

    public String gerarRelatorioMaisVistasGenero() {
        // Quais são as 10 mídias mais vistas, em ordem decrescente separadas por
        // gênero;
        Map<String, String> maisVistasPorGenero = this.audiovisuais.values().stream()
                .sorted(Comparator.comparingInt(Audiovisual::getAudiencia).reversed())
                .limit(10)
                .collect(Collectors.groupingBy(Audiovisual::getGenero,
                        Collectors.mapping(Audiovisual::toString, Collectors.joining(", "))));

        StringBuilder relatorio = new StringBuilder("As 10 mídias mais vistas por gênero são:\n");
        maisVistasPorGenero.forEach(
                (genero, audiovisuais) -> relatorio.append(genero).append(": ").append(audiovisuais).append("\n"));

        return relatorio.toString();
    }
}
