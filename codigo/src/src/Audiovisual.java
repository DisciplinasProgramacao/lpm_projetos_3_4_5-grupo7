package src;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Random;
import java.util.stream.Collectors;

public class Audiovisual {
    protected String nome;
    protected String genero;
    protected String idioma;
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

        this.genero = (Generos.values()[random.nextInt(Generos.values().length)].toString());
        this.idioma = (Idiomas.values()[random.nextInt(Idiomas.values().length)].toString());
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
        return idioma;
    }

    public String getGenero() {
        return genero;
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

    @Override
    public String toString() {
        return String.format(
                "\nId: %d\nNome: %s\nData de Lançamento: %s\nAvaliação: %s\nGênero: %s\nIdioma: %s\nStreams: %d\nTipo: %s\n",
                this.id, this.nome, this.dataLancamento, gerarMediaAvaliacoes(), this.genero,
                this.idioma, this.audiencia, this.tipo.toString());
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