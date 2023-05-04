package src.Models;

import src.Services.DAO;
import src.Services.Filtro;

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

    /**
     * Construtor da PlataformaStreaming. Inicia os HashSets vazios, sem cliente logado e inicia um Filtro
     */
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
    // #endregion

    /**
     * Valida as informações do usuário, efetuando assim seu login caso as informações estejam corretas, e retornando um
     * novo cliente no caso positivo e nulo no caso negativo.
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
     * Retorna uma lista de séries de acordo com um gênero específico
     * 
     * @param genero
     * @return Lista de séries de um gênero específico
     */
    public List<Audiovisual> filtrarPorGenero(String genero) {
        HashSet<Audiovisual> audiovisuais = new HashSet<>();

        audiovisuais.addAll(filmes);
        for (Audiovisual serie : series) {
            audiovisuais.add(serie);
        }
        return this.filtrador.filtrarPorGenero(audiovisuais, genero);
    }

    /**
     * Retorna uma lista de séries de acordo com o idioma informado
     * 
     * @param idioma
     * @return Lista de séries de um idioma específico
     */
    public List<Audiovisual> filtrarPorIdioma(String idioma) {
        HashSet<Audiovisual> audiovisuais = new HashSet<>();
        audiovisuais.addAll(filmes);
        for (Audiovisual serie : series) {

            audiovisuais.add(serie);
        }
        return this.filtrador.filtrarPorIdioma(audiovisuais, idioma);
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
     * Método responsavel por registrar audiência no filme passado
     * @param filme
     */
    public void registrarAudienciaFilme(Filme filme) {
        filmes.stream().filter(x -> x.getNome() == filme.getNome()).findFirst().get().registrarAudiencia();
    }

    /**
     * Método responsavel por registrar audiência na série passada
     * @param serie
     */
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
        Audiovisual AudiovisualEncontrada = null;

        for (Audiovisual filme : this.filmes) {
            for (Audiovisual serie : this.series) {
                if (filme.getNome().equals(nomeAudiovisual)) {
                    AudiovisualEncontrada = filme;
                } else if (serie.getNome().equals(nomeAudiovisual)) {
                    AudiovisualEncontrada = serie;
                }

            }

        }

        return AudiovisualEncontrada;
    }

    // #region salvamento
    /**
     * Implementação da DAO - método de salvar filme no arquivo POO_Filmes.csv
     */
    public void salvarFilme() {
        try {
            DAO<Filme> dao = new DAO<Filme>("POO_Filmes.csv");
            dao.save(this.filmes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Implementação da DAO - método de salvar série no arquivo POO_Series.csv
     */
    public void salvarSerie() {
        try {
            DAO<Serie> dao = new DAO<Serie>("POO_Series.csv");
            dao.save(this.series);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Implementação da DAO - método de salvar cliente no arquivo POO_Espectadores.csv
     */
    public void salvarClientes() {
        try {
            DAO<Cliente> dao = new DAO<Cliente>("POO_Espectadores.csv");
            dao.save(this.clientes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // #endregion
}