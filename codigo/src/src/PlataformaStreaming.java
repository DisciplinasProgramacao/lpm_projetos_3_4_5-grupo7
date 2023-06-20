package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.*;

public class PlataformaStreaming extends Thread {
    private String nome;
    private final HashMap<Integer, Serie> series;
    private final HashMap<Integer, Filme> filmes;
    private final HashMap<String, Cliente> clientes;
    private Cliente clienteAtual;

    public PlataformaStreaming() {
        this.series = new HashMap<>();
        this.filmes = new HashMap<>();
        this.clientes = new HashMap<>();
        this.clienteAtual = null;
    }

    // #region Get Set
    public Cliente getClienteAtual() {
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

    public List<Serie> getListaSerie() {
        return new ArrayList<>(series.values());
    }

    public HashMap<Integer, Audiovisual> getHashMapAudioVisual() {
        HashMap<Integer, Audiovisual> audiovisuais = new HashMap<>();

        audiovisuais.putAll(series);
        audiovisuais.putAll(filmes);
        return audiovisuais;
    }

    public HashMap<String, Cliente> getClientes() {
        return this.clientes;
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
     * @param login String
     * @param senha String
     * @return Retorna um novo cliente
     */
    public Cliente login(String login, String senha) {
        Cliente cliente = this.clientes.get(login);
        return (cliente != null && cliente.getSenha().equals(senha)) ? cliente : null;
    }

    /**
     * Cadastra um novo cliente na Plataforma de Streaming.
     *
     * @param nome  String
     * @param login String
     * @param senha String
     */
    public void cadastro(String nome, String login, String senha, String tipo) {
        Cliente cliente;

        if (tipo == "Comum") {
            cliente = new Cliente(nome, login, senha);
        } else {
            cliente = new Cliente(nome, login, senha, EnumTipoCliente.PROFISSIONAL);
        }
        adicionarCliente(cliente);
    }

    /**
     * Adiciona uma série em uma lista de séries dentro da plataforma
     *
     * @param filme Filme
     */
    public void adicionarFilme(Filme filme) {
        try {
            this.filmes.put(filme.getId(), filme);
            DAO<Filme> dao = new DAO<>("codigo/src/files/POO_Filmes.csv");
            dao.append(filme);
        } catch (Exception cast) {

        }
    }

    /**
     * Adiciona uma série em uma lista de séries dentro da plataforma
     *
     * @param serie Serie
     */
    public void adicionarSerie(Serie serie) {
        this.series.put(serie.getId(), serie);
    }

    /**
     * Adiciona um cliente em uma lista de clientes dentro da plataforma
     * 
     * @param cliente Cliente
     */
    private void adicionarCliente(Cliente cliente) {
        this.clientes.put(cliente.getLogin(), cliente);
        DAO<Cliente> dao = new DAO<>("codigo/src/files/POO_Espectadores.csv");
        try {
            dao.append(cliente);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * O método acima retorna uma série de acordo com o nome informado
     * 
     * @param nomeAudiovisual String
     * @return Audiovisual
     */
    public Audiovisual buscarAudiovisual(String nomeAudiovisual) {
        for (Audiovisual audio : getListaAudioVisual()) {
            if (audio.getNome().equalsIgnoreCase(nomeAudiovisual))
                return audio;
        }
        return null;
    }

    /**
     * O método acima retorna uma série de acordo com o id informado
     * 
     * @param id int
     * @return Audiovisual
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
            DAO<Cliente> dao = new DAO<>("codigo/src/files/POO_Espectadores.csv");
            dao.save(this.clientes.values());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Salva as listas de Assistidas e ParaAssistir de cada cliente em um arquivo
     * 
     * @param cliente Cliente
     */
    public void salvarListasCliente(Cliente cliente) {
        try {
            DAO<Cliente> daoCliente = new DAO<>("codigo/src/files/POO_Audiencia.csv");

            // for (Cliente cliente : this.clientes.values()) {
            List<String> listaParaAssistir = new ArrayList<>();
            List<String> listaJaVistas = new ArrayList<>();

            for (Audiovisual audiovisual : cliente.getParaVer().values()) {
                String item = String.valueOf(audiovisual.getId());
                listaParaAssistir.add(item);
            }

            for (Audiovisual audiovisual : cliente.getAssistidas().values()) {
                String item = String.valueOf(audiovisual.getId());
                listaJaVistas.add(item);
            }

            daoCliente.appendData(cliente.getLogin(), "F", listaParaAssistir);
            daoCliente.appendData(cliente.getLogin(), "A", listaJaVistas);
            // }
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
            processaBlocoAudiencia(lista);
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
                    try {
                        cliente.adicionarNaListaJaVistas(audiovisual);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }

        }
    }

    // #endregion
}