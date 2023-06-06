package src;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Random;
import java.util.stream.Collectors;

public class Audiovisual {
    private String nome;
    private String genero;
    private String idioma;
    private LocalDate dataAssistido;
    private int audiencia;
    private int id;
    private String dataLancamento;
    private HashMap<String, Avaliacao> avaliacoes;

    public Audiovisual() {}

    public enum Generos {
        TERROR, COMEDIA, ROMANCE, SUSPENSE, ACAO, FICCAO_CIENTIFICA, DRAMA
    }

    public enum Idiomas {
        PORTUGUES, ITALIANO, FRANCES, INGLES, ESPANHOL
    }

    /**
     * Construtor audiovisual que recebe as informações básicas do filme ou série
     * 
     * @param id int
     * @param nome String
     * @param dataLancamento String
     */
    Audiovisual(int id, String nome, String dataLancamento) {

        Random random = new Random();

        this.genero = (Generos.values()[random.nextInt(Generos.values().length)].toString());
        this.idioma = (Idiomas.values()[random.nextInt(Idiomas.values().length)].toString());
        setNome(nome);
        setDataLancamento(dataLancamento);
        setId(id);
        this.avaliacoes = new HashMap<>();
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

    public LocalDate getDataAssistido() {
        return dataAssistido;
    }

    public void setDataAssistido() {
        this.dataAssistido = LocalDate.now();
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public HashMap<String, Avaliacao> getAvaliacoes() {
        return this.avaliacoes;
    }

    @Override
    public String toString() {
        return String.format("\nId: %d\nNome: %s\nData de Lançamento: %s\nAvaliação: %s\nGênero: %s\nIdiomar: %s",
                this.getId(), this.getNome(), getDataLancamento(), gerarMediaAvaliacoes(), this.getGenero(),
                this.getIdioma());
    }
    // #endregion

    // #region avaliações
    /**
     * Metodo responsavel por retornar todas avaliações
     * 
     * @return String
     */
    public String mostrarAvaliacoes() {
        StringBuilder stringAvaliacoes = new StringBuilder();

        this.avaliacoes.values().forEach(avaliacao -> stringAvaliacoes.append(avaliacao.toString()));

        return stringAvaliacoes.toString();
    }

    /**
     * Metodo responsavel por adicionar um comentario sem repetir
     * 
     * @param cliente Cliente
     * @param avaliacao Avaliacao
     */
    public void adicionarAvaliacao(Cliente cliente, Avaliacao avaliacao) {
        this.avaliacoes.put(cliente.getLogin(), avaliacao);
    }

    /**
     * Método responsável por gerar a média das avaliações
     * 
     * @return double
     */
    public double gerarMediaAvaliacoes() {
        return this.avaliacoes.values().stream().collect(Collectors.averagingDouble(Avaliacao::getNota));
    }
    // #endregion
}