package src;

public class Serie extends Audiovisual implements IDAO {
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

    @Override
    public String stringSalvar() {
        return String.format("%o;%s;%s", this.getId(), this.getNome(), this.getDataLancamento());
    }

    public int getQuantidadeEpisodios() {
        return quantidadeEpisodios;
    }

    public void setQuantidadeEpisodios(int quantidadeEpisodios) {
        this.quantidadeEpisodios = quantidadeEpisodios;
    }
}