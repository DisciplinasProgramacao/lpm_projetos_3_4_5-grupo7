package src;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

    /**
     * Gera relatório de cliente que fez mais de 15 avaliações
     *
     * @return Relatorio com o cliente que fez mais de 15 avaliações
     */
    public String gerarRelatorioClientes15Avaliacoes(Predicate<Cliente> filtro) {
        List<Cliente> listaClientes = new ArrayList<>(clientes.values());
        int quantidadeCliente = (int) listaClientes.stream()
                .filter(filtro)
                .count();

        double porcentagem = (double) quantidadeCliente / listaClientes.size() * 100;
        return "A porcentagem de clientes com no mínimo 15 avaliações é de: " + porcentagem + "%";
    }

    /**
     * Gera relatório das 10 mídias de melhor avaliação
     *
     * @param filtro Predicate<Audiovisual>
     * @param media  Comparator<Audiovisual>
     * @return Relatório das 10 mídias de melhor avaliação
     */
    public String gerarRelatorioDezMelhores(Predicate<Audiovisual> filtro, Comparator<Audiovisual> media) {
        String audiovisualList = this.audiovisuais.values().stream()
                .filter(filtro)
                .sorted(media)
                .limit(10)
                .map(Audiovisual::toString)
                .collect(Collectors.joining(", "));

        return "As 10 mídias de melhor avaliação são: " + audiovisualList;
    }

    /**
     * Gera relatório das 10 mídias mais vistas
     *
     * @param comparator Comparator<Audiovisual>
     * @return Relatório das 10 mídias mais vistas
     */
    public String gerarRelatorio10MaisVistas(Comparator<Audiovisual> comparator) {
        String audiovisualList = this.audiovisuais.values().stream()
                .sorted(comparator)
                .limit(10)
                .map(Audiovisual::toString)
                .collect(Collectors.joining(", "));

        return "As 10 mídias mais vistas são: " + audiovisualList;
    }

    /**
     * Gera relatório com os 10 melhores gêneros
     *
     * @param filtro                Predicate<Audiovisual>
     * @param comparator            Comparator<Audiovisual>
     * @param groupingByAudiovisual Collector<Audiovisual, ?, Map<String, String>>
     * @return Relatório dos 10 melhore gêneros
     */
    public String gerarRelatorioDezMelhoresGenero(
            Predicate<Audiovisual> filtro,
            Comparator<Audiovisual> comparator,
            Collector<Audiovisual, ?, Map<String, String>> groupingByAudiovisual) {
        Map<String, String> audiovisualMap = this.audiovisuais.values().stream()
                .filter(filtro)
                .sorted(comparator)
                .limit(10)
                .collect(groupingByAudiovisual);

        StringBuilder relatorio = new StringBuilder("As 10 mídias de melhor avaliação por gênero são:\n");
        audiovisualMap.forEach((genero, audiovisuais) -> relatorio.append(genero)
                .append(": ")
                .append(audiovisuais)
                .append("\n"));

        return relatorio.toString();
    }

    /**
     * Gera relatório com os gêneros mais vistos
     *
     * @param comparator            Comparator<Audiovisual>
     * @param groupingByAudiovisual Collector<Audiovisual, ?, Map<String, String>>
     * @return Relatório dos gêneros mais vistos
     */
    public String gerarRelatorioMaisVistasGenero(
            Comparator<Audiovisual> comparator,
            Collector<Audiovisual, ?, Map<String, String>> groupingByAudiovisual) {
        Map<String, String> audiovisualMap = this.audiovisuais.values().stream()
                .sorted(comparator)
                .limit(10)
                .collect(groupingByAudiovisual);

        StringBuilder relatorio = new StringBuilder("As 10 mídias mais vistas por gênero são:\n");

        audiovisualMap.forEach((genero, audiovisuais) -> relatorio.append(genero)
                .append(":")
                .append(audiovisuais)
                .append("\n"));

        return relatorio.toString();
    }
}
