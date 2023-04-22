
import java.util.Random;

public class Audiovisual {
    private static String[] GENEROS;
    private String nome;
    private String genero;
    private String idioma;
    private int audiencia;

    Audiovisual(String nome) {
        GENEROS = new String[]{"Terror", "Comédia", "Romance", "Suspense", "Ação", "Ficção Científica", "Drama"};
        Random random = new Random();

        this.genero = GENEROS[(random.nextInt(7))];

        this.nome = nome;
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

}