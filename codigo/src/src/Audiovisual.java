package src;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Random;
import java.util.stream.Collectors;

public abstract class Audiovisual {
    protected String nome;
    protected Generos genero;
    protected Idiomas idioma;
    protected LocalDate dataAssistido;
    protected int audiencia;
    protected int id;
    protected String dataLancamento;
    protected HashMap<String, Avaliacao> avaliacoes;
    protected Tipo tipo;

    public Audiovisual() {
    }

    /**
     * Construtor audiovisual que recebe as informações básicas do filme ou série
     * 
     * @param id             int
     * @param nome           String
     * @param dataLancamento String
     */
    Audiovisual(int id, String nome, String dataLancamento) {
        init(id, nome, dataLancamento);
        this.tipo = Tipo.REGULAR;
    }

    /**
     * Construtor audiovisual que recebe o audiovisual e o tipo (Restrito ou
     * regular)
     * 
     * @param id             int
     * @param nome           String
     * @param dataLancamento String
     */
    Audiovisual(int id, String nome, String dataLancamento, Tipo tipo) {
        init(id, nome, dataLancamento);
        this.tipo = tipo;
    }

    public void init(int idAudiovisual, String nome, String lancamento) {
        Random random = new Random();

        this.genero = (Generos.values()[random.nextInt(Generos.values().length)]);
        this.idioma = (Idiomas.values()[random.nextInt(Idiomas.values().length)]);
        this.nome = nome;
        this.dataLancamento = lancamento;
        this.id = idAudiovisual;
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

    // #region getters para FILTROS
    public String getIdioma() {
        return idioma.name();
    }

    public String getGenero() {
        return genero.name();
    }

    public int getAudiencia() {
        return audiencia;
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return this.id;
    }
    // #endregion

    // usados para verificação de especialista do cliente (data assistido)
    public LocalDate getDataAssistido() {
        return dataAssistido;
    }

    public void setDataAssistido() {
        this.dataAssistido = LocalDate.now();
    }

    public String getTipo() {
        return tipo.toString();
    }

    public HashMap<String, Avaliacao> getAvaliacoes() {
        return this.avaliacoes;
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
     * @param cliente   Cliente
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