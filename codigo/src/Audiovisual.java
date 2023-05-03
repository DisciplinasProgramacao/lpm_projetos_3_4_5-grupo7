package src;

import java.util.Random;

public class Audiovisual  {
    private static String[] GENEROS;
    private String nome;
    private String genero;
    private String idioma;
    private int audiencia;
    private int id;
    private String dataLancamento;

    Audiovisual(int id, String nome, String dataLancamento) {
        GENEROS = new String[] { "Terror", "Comédia", "Romance", "Suspense", "Ação", "Ficção Científica", "Drama" };
        Random random = new Random();

        setGenero(GENEROS[(random.nextInt(7))]);
        setNome(nome);
        setDataLancamento(dataLancamento);
        setId(id);
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

    public String getGenero() {
        return genero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(String dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}