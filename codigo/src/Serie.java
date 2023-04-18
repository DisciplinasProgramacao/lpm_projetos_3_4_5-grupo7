package src;

public class Serie {
    private static String[] GENEROS;
    private String nome;
    private String genero;
    private String idioma;
    private int quantidadeEpisodios;
    private int audiencia;

    Serie() {
        this.audiencia = 0;
    }

    /**
     * A cada cliente conectado, é incrementado 1 na audiência
     */
    public void registrarAudiencia() {
        this.audiencia++;
    }

    public int getAudiencia() {
        return audiencia;
    }

    public String getIdioma() {
        return idioma;
    }

    public int getQuantidadeEpisodios() {
        return quantidadeEpisodios;
    }

    public String getGenero() {
        return genero;
    }

    public String getNome() {
        return nome;
    }
}