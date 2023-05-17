package src;

import src.Avaliacao;

import java.util.HashMap;
import java.util.Random;

public class Audiovisual {
    private static String[] GENEROS;
    private String nome;
    private String genero;
    private String idioma;
    private int audiencia;
    private int id;
    private String dataLancamento;
    private HashMap<String, Avaliacao> avaliacoes;

    /**
     * Construtor audiovisual que recebe as informações básicas do filme ou série
     * 
     * @param id
     * @param nome
     * @param dataLancamento
     */
    Audiovisual(int id, String nome, String dataLancamento) {
        GENEROS = new String[] { "Terror", "Comédia", "Romance", "Suspense", "Ação", "Ficção Científica", "Drama" };
        Random random = new Random();

        setGenero(GENEROS[(random.nextInt(7))]);
        setNome(nome);
        setDataLancamento(dataLancamento);
        setId(id);
        this.avaliacoes = new HashMap<String, Avaliacao>();
        this.audiencia = 0;
    }

    /**
     * Método que incrementa em 1 a audiência a cada cliente conectado
     */
    public void registrarAudiencia() {
        this.audiencia++;
    }

    // #region getters/setters
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

    @Override
    public String toString() {
        return String.format("\nNome: %s\nData de Lançamento: %s\nAvaliação: %s\nGênero: %s",
                this.getNome(), getDataLancamento(), gerarMediaAvaliacoes(), this.getGenero());
    }
    // #endregion

    // #region avaliações
    /**
     * Metodo responsavel por retornar todas avaliações
     * 
     * @return
     */
    public String mostrarAvaliacoes() {
        StringBuilder stringAvaliacoes = new StringBuilder();

        this.avaliacoes.values().forEach(avaliacao -> stringAvaliacoes.append(avaliacao.toString()));

        return stringAvaliacoes.toString();
    }

    /**
     * Metodo responsavel por adicionar um comentario sem repetir
     * 
     * @param login
     * @param nota
     * @param comentario
     */
    public void adicionarAvaliacao(Cliente cliente, Double nota, String comentario) {
        if (cliente.verificarEspecialista())
            this.avaliacoes.put(cliente.getLogin(), new Avaliacao(nota, comentario));
        else
            this.avaliacoes.put(cliente.getLogin(), new Avaliacao(nota));
    }

    /**
     * Método responsável por gerar a média das avaliações
     * 
     * @return double
     */
    public double gerarMediaAvaliacoes() {
        return this.avaliacoes.values().stream().mapToDouble(num -> num.getNota()).average().getAsDouble();
    }
    // #endregion
}