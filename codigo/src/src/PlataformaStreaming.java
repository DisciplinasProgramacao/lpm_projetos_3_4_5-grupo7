package src;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.io.*;

public class PlataformaStreaming {
    private String nome;
    private HashSet<Serie> series;
    private HashSet<Filme> filmes;
    private HashSet<Cliente> clientes;
    private Cliente clienteAtual;
    private Filtro filtrador;

    public PlataformaStreaming() {
        this.series = new HashSet<Serie>();
        this.filmes = new HashSet<Filme>();
        this.clientes = new HashSet<Cliente>();
        this.clienteAtual = null;
        this.filtrador = new Filtro();
    }

    // #region Get Set
    public Cliente getcClienteAtual() {
        return this.clienteAtual;
    }

    public String getNome() {
        return this.nome;
    }

    public List<Audiovisual> getListaAudioVisual() {
        List<Audiovisual> audiovisuais = new ArrayList<>();
        audiovisuais.addAll(series);
        audiovisuais.addAll(filmes);
        return audiovisuais;
    }
    // #endregion

    /**
     * Valida as informações do usuário, efetuando assim seu login e retornando um
     * novo cliente
     * 
     * @param nomeUsuario
     * @param senha
     * @return Retorna um novo cliente
     */
    public Cliente login(String nomeUsuario, String senha) {
        for (Cliente cliente : this.clientes)
            if (cliente.getSenha() == senha && cliente.getNomeUsuario() == nomeUsuario) {
                this.clienteAtual = cliente;
                return cliente;
            }

        return null;
    }

    /**
     * Adiciona uma série em uma lista de séries dentro da plataforma
     *
     * @param filme
     */
    public void adicionarFilme(Filme filme) {
        this.filmes.add(filme);
    }

    /**
     * Adiciona uma série em uma lista de séries dentro da plataforma
     *
     * @param serie
     */
    public void adicionarSerie(Serie serie) {
        this.series.add(serie);
    }

    /**
     * Adiciona um cliente em uma lista de clientes dentro da plataforma
     * 
     * @param cliente
     */
    public void adicionarCliente(Cliente cliente) {
        this.clientes.add(cliente);
        DAO<Cliente> dao = new DAO<Cliente>("POO_Filmes.csv");

        try {
            dao.append(cliente);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retorna uma lista de séries de acordo com a quantidade de episódeos informada
     * 
     * @param quantEpisodios
     * @return Lista de séries de uma determinada quantidade de episódeos
     */
    public List<Serie> filtrarPorQtdEpisodios(int quantEpisodios) {
        List<Serie> seriesFiltradas = new ArrayList<>();

        for (Serie serie : this.series)
            if (serie.getQuantidadeEpisodios() == quantEpisodios)
                seriesFiltradas.add(serie);

        return seriesFiltradas;
    }

    /**
     * 
     * metodo responsavel por registrar audiencia de acordo com o
     * objeto Audiovisual passado
     * 
     * @param filme
     */
    public void registrarAudienciaFilme(Filme filme) {
        filmes.stream().filter(x -> x.getNome() == filme.getNome()).findFirst().get().registrarAudiencia();
    }

    public void registrarAudienciaSerie(Serie serie) {
        series.stream().filter(x -> x.getNome() == serie.getNome()).findFirst().get().registrarAudiencia();
    }

    /**
     * Desconecta/desloga o usuário da plataforma
     */
    public void logoff() {
        this.clienteAtual = null;
    }

    /**
     * O método acima retorna uma série de acordo com o nome informado
     * 
     * @param nomeAudiovisual
     * @return Retorna uma série específica
     */
    public Audiovisual buscarAudiovisual(String nomeAudiovisual) {
        List<Audiovisual> lista = new ArrayList<Audiovisual>();
        lista.addAll(this.series);
        lista.addAll(this.filmes);
        return lista.stream().filter(x -> x.getNome().equals(nomeAudiovisual)).findFirst().get();
    }

    /**
     * O método acima retorna uma série de acordo com o id informado
     * 
     * @param id
     * @return Retorna uma série específica
     */
    public Audiovisual buscarAudiovisual(int id) {
        List<Audiovisual> lista = new ArrayList<Audiovisual>();
        lista.addAll(this.series);
        lista.addAll(this.filmes);
        return lista.stream().filter(x -> x.getId() == id).findFirst().get();
    }

    // #region persistem
    public void salvarFilme() {
        try {
            DAO<Filme> dao = new DAO<>("POO_Filmes.csv");
            dao.save(this.filmes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void salvarSerie() {
        try {
            DAO<Serie> dao = new DAO<>("POO_Series.csv");
            dao.save(this.series);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void salvarClientes() {
        try {
            DAO<Cliente> dao = new DAO<>("POO_Espectadores.csv");
            dao.save(this.clientes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // #endregion
}