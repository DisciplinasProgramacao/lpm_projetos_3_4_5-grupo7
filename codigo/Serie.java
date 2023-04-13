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

    public void registrarAudiencia() {
        this.audiencia++;
    }
}