
public class Serie extends Audiovisual{
    private int quantidadeEpisodios;


    Serie(String nome) {
        super(nome);
    }

    /**
     * A cada cliente conectado, é incrementado 1 na audiência
     */


    public int getQuantidadeEpisodios() {
        return quantidadeEpisodios;
    }

    public void setQuantidadeEpisodios(int quantidadeEpisodios) {
        this.quantidadeEpisodios = quantidadeEpisodios;
    }
}