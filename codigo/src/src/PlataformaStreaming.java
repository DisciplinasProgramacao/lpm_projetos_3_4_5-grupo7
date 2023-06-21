package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.*;
import java.util.Objects;

public class PlataformaStreaming extends Thread {
    private String nome;
    private final HashMap<Integer, Serie> series;
    private final HashMap<Integer, Filme> filmes;
    private final HashMap<String, Cliente> clientes;

    public PlataformaStreaming() {
        this.series = new HashMap<>();
        this.filmes = new HashMap<>();
        this.clientes = new HashMap<>();
    }

    // #region Get Set

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

        if (Objects.equals(tipo, "Comum")) {
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
        try {
            this.series.put(serie.getId(), serie);
            DAO<Serie> dao = new DAO<>("codigo/src/files/POO_Series.csv");
            dao.append(serie);
        } catch (Exception cast) {
        }
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

    public void salvarAudiencia() {
        try {
            DAO<Cliente> dao = new DAO<>("codigo/src/files/POO_Audiencia.csv");
            List<String> listaSave = new ArrayList<>();

            this.clientes.values().forEach(cliente -> {
                cliente.getParaVer().values().forEach(x -> listaSave.add(cliente.getLogin() + ";" + "F;" + x.getId()));
                cliente.getAssistidas().values()
                        .forEach(x -> listaSave.add(cliente.getLogin() + ";" + "A;" + x.getId()));
            });

            dao.save(listaSave);
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
                    cliente.adicionarNaLista(audiovisual, false);
                else
                    try {
                        cliente.adicionarNaListaJaVistas(audiovisual, false);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }

        }
    }

    // #endregion
}