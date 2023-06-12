package src;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.joining;

public class Relatorio {
    private final PlataformaStreaming plataforma;

    public Relatorio(PlataformaStreaming plataforma) {
        this.plataforma = plataforma;
    }

    public String gerarRelatorioDeMidia() {

        Cliente clienteTopMidia = plataforma.getClientes().values().stream()
                .max(Comparator.comparingInt(cliente -> cliente.getAssistidas().size())).get();

        return "O cliente que assistiu mais mídias foi o " + clienteTopMidia.getLogin() + ", com "
                + clienteTopMidia.getAssistidas().size() + " assistidos";
    }

    // TODO - corrigir: gerando null pointer exception
    public String gerarRelatorioAvaliacao() {
        // giving null pointer exception

        Cliente clienteTopAvaliacao = plataforma.getClientes().values().stream()
                .filter(cliente -> cliente.getAvaliacoes() != null)
                .max(Comparator.comparingInt(cliente -> cliente.getAvaliacoes().size())).get();

        return "O cliente que avaliou mais mídias foi o " + clienteTopAvaliacao.getLogin() + ", com "
                + clienteTopAvaliacao.getAvaliacoes().size() + " avalidadas";
    }

    // TODO - corrigir: gerando null pointer exception
    public String gerarRelatorioClientes15Avaliacoes() {
        List<Cliente> clientes15Avaliacoes = plataforma.getClientes().values().stream()
                .filter(cliente -> cliente.getAvaliacoes().size() >= 15).collect(Collectors.toList());

        double porcentagem = (double) clientes15Avaliacoes.size() / plataforma.getClientes().size() * 100;
        return "A porcentagem de clientes que avaliaram mais de 15 mídias é de " + porcentagem + "%";
    }

    // TODO - corrigir: não está imprimindo os audiovisuais
    public String gerarRelatorioDezMelhores() {
        // Quais são as 10 mídias de melhor avaliação, com pelo menos 100 avaliações, em
        // ordem decrescente;

        String lista10Audiovisuais = plataforma.getListaAudioVisual().stream()
                .filter(audiovisual -> audiovisual.getAvaliacoes().size() >= 100)
                .sorted(Comparator.comparingDouble(Audiovisual::gerarMediaAvaliacoes).reversed()).limit(10)
                .map(audiovisual -> audiovisual.toString()).collect(joining(", "));

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
