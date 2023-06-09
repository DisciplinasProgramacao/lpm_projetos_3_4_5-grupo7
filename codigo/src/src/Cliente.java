package src;

import java.io.IOException;
import java.io.InvalidClassException;
import java.util.HashMap;
import java.util.HashSet;

public class Cliente implements IDAO<Cliente> {
    private String nomeDeUsuario;
    private String senha;
    private String login;
    private HashMap<Integer, Audiovisual> listaParaVer;
    private HashMap<Integer, Audiovisual> listaJaVistas;
    private HashSet<Avaliacao> avaliacoes;
    private EnumTipoCliente tipo;

    public Cliente() {
        this.tipo = EnumTipoCliente.REGULAR;
    }

    public void init(String usuario, String senha, String nome) {
        this.nomeDeUsuario = nome;
        this.login = usuario;
        this.senha = senha;
        this.listaParaVer = new HashMap<>();
        this.listaJaVistas = new HashMap<>();
        this.avaliacoes = new HashSet<>();
    }

    public void init(String usuario, String senha, String nome, EnumTipoCliente tipo) {
        this.nomeDeUsuario = nome;
        this.login = usuario;
        this.senha = senha;
        this.listaParaVer = new HashMap<>();
        this.listaJaVistas = new HashMap<>();
        this.avaliacoes = new HashSet<>();
        this.tipo = tipo;
    }

    /**
     * Construtor do cliente, recebendo usuário e senha e inicializando as listas
     * vazias
     * 
     * @param usuario String
     * @param senha   String
     */
    public Cliente(String usuario, String senha) {
        init(usuario, senha, usuario);
    }

    /**
     * Construtor do cliente, recebendo usuário e senha e inicializando as listas
     * vazias
     *
     * @param nome    String
     * @param usuario String
     * @param senha   String
     */
    public Cliente(String nome, String usuario, String senha) {
        init(usuario, senha, nome);
    }

    public Cliente(String nome, String usuario, String senha, EnumTipoCliente tipo) {
        init(usuario, senha, nome);
        this.tipo = tipo;
    }

    /**
     * Adiciona uma avaliação a um audiovisual
     * 
     * @param aud
     * @param nota
     * @param comentario
     * @throws Exception
     */
    public void adicionarAvaliacao(Audiovisual aud, double nota, String comentario) throws InvalidClassException {
        Avaliacao avaliacao = new Avaliacao(nota);
        IComentarista tipoEspecialista;

        aud.adicionarAvaliacao(this, avaliacao);
        this.avaliacoes.add(avaliacao);

        try {
            tipoEspecialista = (IComentarista) tipo;
            tipoEspecialista.comentar(avaliacao, comentario);
        } catch (InvalidClassException e) {
            throw new InvalidClassException("Este cliente não pode comentar");
        }
    }

    /**
     * Adiciona uma série em uma lista de séries para serem assistidas
     * 
     * @param audiovisual Audiovisual
     */
    public void adicionarNaLista(Audiovisual audiovisual, boolean salvar) {
        if (listaParaVer.get(audiovisual.getId()) == null) {
            listaParaVer.put(audiovisual.getId(), audiovisual);
            if (salvar) {
                try {
                    DAO<Cliente> daoCliente = new DAO<>("codigo/src/files/POO_Audiencia.csv");
                    daoCliente.appendData(this.login + ";" + "F;" + audiovisual.getId());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Adiciona uma série em uma lista de séries que já foram assistidas pelo
     * cliente
     * 
     * @param salvar      Indica se os dados devem ser salvos (padrão: false)
     * @param audiovisual Audiovisual
     */
    public void adicionarNaListaJaVistas(Audiovisual audiovisual, boolean salvar) throws IllegalArgumentException {

        if (audiovisual.getTipo() != "REGULAR" && this.tipo != EnumTipoCliente.PROFISSIONAL)
            throw new IllegalArgumentException("Apenas clientes profissionais podem assistir lançamentos!");

        audiovisual.setDataAssistido();
        audiovisual.registrarAudiencia();

        if (listaJaVistas.get(audiovisual.getId()) == null) {
            listaJaVistas.put(audiovisual.getId(), audiovisual);
            if (salvar) {
                try {
                    DAO<Cliente> daoCliente = new DAO<>("codigo/src/files/POO_Audiencia.csv");
                    daoCliente.appendData(this.login + ";" + "A;" + audiovisual.getId());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (tipo == null) {
            tipo = EnumTipoCliente.REGULAR;
        }

        this.tipo = tipo.verificarEspecialista(listaJaVistas);
    }

    /**
     * Remove uma série em uma lista de séries para serem assistidas
     * 
     * @param id int
     */
    public void retirarDaLista(int id) {
        listaParaVer.values().removeIf(x -> x.getId() == id);
    }

    /**
     * Implementação do método da interface: salva no arquivo de maneira formatada a
     * linha dos dados do cliente
     * 
     * @return String
     */
    @Override
    public String stringSalvar() {

        char tipoCliente = tipo == null ? 'R' : this.tipo.name().charAt(0);

        return String.format("%s;%s;%s;%c", this.nomeDeUsuario, this.login, this.senha, tipoCliente);
    }

    /**
     * Implementação do método da interface: carrega o obejto formatado com a
     * linha dos dados do cliente
     *
     * @param linha String
     * @return Cliente
     */
    @Override
    public Cliente loadObject(String linha) {
        String[] dados = linha.split(";");

        String nome = dados[0].trim();
        String login = dados[1].trim();
        String senha = dados[2].trim();
        EnumTipoCliente tipo = null;
        if (dados.length > 3) {
            switch (dados[3].trim()) {
                case "P":
                    tipo = EnumTipoCliente.PROFISSIONAL;
                    break;
                case "E":
                    tipo = EnumTipoCliente.ESPECIALISTA;
                    break;
            }
        }

        return new Cliente(nome, login, senha, tipo);
    }


    // #region getters

    // utilizada para Login na plataforma
    public String getSenha() {
        return this.senha;
    }

    public Audiovisual getMidiaAssistida(int idmidia) {
        return this.listaJaVistas.get(idmidia);
    }

    public String getNome() {
        return this.nomeDeUsuario;
    }

    public String getLogin() {
        return this.login;
    }

    public HashMap<Integer, Audiovisual> getParaVer() {
        return this.listaParaVer;
    }

    public HashMap<Integer, Audiovisual> getAssistidas() {
        return this.listaJaVistas;
    }

    public HashSet<Avaliacao> getAvaliacoes() {
        return this.avaliacoes;
    }

    public int getTotalAvaliacoes() {
        return this.avaliacoes.size();
    }
    // #endregion

    @Override
    public String toString() {
        String tipo = "Regular";

        try {
            tipo = this.tipo.name();
        } catch (Exception e) {
        }

        return String.format("Bem vindo " + this.nomeDeUsuario + "! Seu login é "
                + this.login + " e sua senha é " + this.senha + ".\n" + "Você é um cliente "
                + tipo);
    }
}
