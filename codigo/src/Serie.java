package src;

public class Serie extends Audiovisual {
    private int quantidadeEpisodios;

    Serie(int id, String nome, String dataLancamento) {
        super(id, nome, dataLancamento);
    }

    /**
     * A cada cliente conectado, é incrementado 1 na audiência
     */

    public String getNome() {
        return getNome();
    }

    public int getQuantidadeEpisodios() {
        return quantidadeEpisodios;
    }

    public void setQuantidadeEpisodios(int quantidadeEpisodios) {
        this.quantidadeEpisodios = quantidadeEpisodios;
    }
}