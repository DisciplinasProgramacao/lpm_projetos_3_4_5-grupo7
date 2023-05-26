package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;
import java.io.*;

public class PlataformaStreaming extends Thread {
    private String nome;
    private HashMap<Integer, Serie> series;
    private HashMap<Integer, Filme> filmes;
    private HashMap<String, Cliente> clientes;
    private Cliente clienteAtual;

    public PlataformaStreaming() {
        this.series = new HashMap<Integer, Serie>();
        this.filmes = new HashMap<Integer, Filme>();
        this.clientes = new HashMap<String, Cliente>();
        this.clienteAtual = null;
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

        audiovisuais.addAll(series.values());
        audiovisuais.addAll(filmes.values());
        return audiovisuais;
    }

    public HashMap<Integer, Audiovisual> getHashMapAudioVisual() {
        HashMap<Integer, Audiovisual> audiovisuais = new HashMap<>();

        audiovisuais.putAll(series);
        audiovisuais.putAll(filmes);
        return audiovisuais;
    }

    public HashMap<Integer, Filme> getFilmes() {
        return this.filmes;
    }

    public HashMap<Integer, Serie> getSeries() {
        return this.series;
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
    public Cliente login(String login, String senha) {
        for (Cliente cliente : this.clientes.values())
            if (cliente.getSenha().equals(senha) && cliente.getLogin().equals(login)) {
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
        this.filmes.put(filme.getId(), filme);
    }

    /**
     * Adiciona uma série em uma lista de séries dentro da plataforma
     *
     * @param serie
     */
    public void adicionarSerie(Serie serie) {
        this.series.put(serie.getId(), serie);
    }

    /**
     * Adiciona um cliente em uma lista de clientes dentro da plataforma
     * 
     * @param cliente
     */
    public void adicionarCliente(Cliente cliente) {
        this.clientes.put(cliente.getLogin(), cliente);
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

        for (Serie serie : this.series.values())
            if (serie.getQuantidadeEpisodios() == quantEpisodios)
                seriesFiltradas.add(serie);

        return seriesFiltradas;
    }

    /**
     * 
     * Método responsavel por registrar audiencia de acordo com o
     * objeto Audiovisual passado
     * 
     * @param filme
     */
    public void registrarAudienciaFilme(Filme filme) {
        filmes.values().stream().filter(x -> x.getNome() == filme.getNome()).findFirst().get().registrarAudiencia();
    }

    public void registrarAudienciaSerie(Serie serie) {
        series.values().stream().filter(x -> x.getNome() == serie.getNome()).findFirst().get().registrarAudiencia();
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
        for (Audiovisual audio : getListaAudioVisual()) {
            if (audio.getNome().toLowerCase().equals(nomeAudiovisual.toLowerCase()))
                return audio;
        }
        return null;
    }

    /**
     * O método acima retorna uma série de acordo com o id informado
     * 
     * @param id
     * @return Retorna uma série específica
     */
    public Audiovisual buscarAudiovisual(int id) {
        return this.getHashMapAudioVisual().get(id);
    }

    // #region persistem - salvamento em arquivo
    /**
     * Salva um filme no arquivo
     */
    public void salvarFilme() {
        try {
            DAO<Filme> dao = new DAO<>("codigo/src/files/POO_Filmes.csv");
            dao.save(this.filmes.values());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Salva uma série no arquivo
     */
    public void salvarSerie() {
        try {
            DAO<Serie> dao = new DAO<>("codigo/src/files/POO_Series.csv");
            dao.save(this.series.values());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Salva um cliente no arquivo
     */
    public void salvarClientes() {
        try {
            DAO<Cliente> dao = new DAO<>("POO_Espectadores.csv");
            dao.save(this.clientes.values());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void carregarDados() {
        try {
            DAO<Filme> daoFilme = new DAO<>("codigo/src/files/POO_Filmes.csv");
            DAO<Serie> daoSerie = new DAO<>("codigo/src/files/POO_Series.csv");
            DAO<Cliente> daoCliente = new DAO<>("codigo/src/files/POO_Espectadores.csv");
            daoFilme.load(new Filme()).forEach(x -> this.filmes.put(x.getId(), x));
            daoSerie.load(new Serie()).forEach(x -> this.series.put(x.getId(), x));
            daoCliente.load(new Cliente()).forEach(x -> this.clientes.put(x.getLogin(), x));
            carregarAudiencia();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void carregarAudiencia() {
        try {
            DAO<Filme> daoAudiencia = new DAO<>("codigo/src/files/POO_Audiencia.csv");
            List<String> lista = daoAudiencia.load();
            int contador = 1;
            int tamanhoLista = lista.size();
            int restoDivisao = tamanhoLista % 4;
            int quantidadeThreads = 4;
            int tamanhoBlocos = tamanhoLista > 10000 ? ((tamanhoLista - restoDivisao) / quantidadeThreads)
                    : tamanhoLista;
            int auxBloco = 1;
            int contadorLista = 1;
            List<String> auxLista = new Stack<>();
            for (String linha : lista) {

                if (contador != tamanhoBlocos && contadorLista != tamanhoLista) {
                    contador++;
                    auxLista.add(linha);
                } else {
                    auxBloco++;
                    contador = 0;
                    // new Thread(()-> processaBlocoAudiencia(new
                    // List<String>().addAll(auxLista))).start();
                    processaBlocoAudiencia(auxLista);
                    auxLista = new Stack<>();
                }

                if (auxBloco == quantidadeThreads) {
                    tamanhoBlocos += restoDivisao;
                }
                contadorLista++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processaBlocoAudiencia(List<String> bloco) {
        for (String linha : bloco) {
            String[] dados = linha.split(";");
            String login = dados[0];
            String opc = dados[1];
            int idAudio = Integer.parseInt(dados[2]);

            Audiovisual audiovisual = getHashMapAudioVisual().get(idAudio);
            Cliente cliente = this.clientes.get(login);

            if (cliente != null && audiovisual != null) {
                if (opc.equals("F"))
                    cliente.adicionarNaLista(audiovisual);
                else
                    cliente.adicionarNaListaJaVistas(audiovisual);
            }

        }
    }

    public void run() {

    }
    // #endregion
}