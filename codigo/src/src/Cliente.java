package src;

import java.time.LocalDate;
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
    }

    /**
     * Construtor do cliente, recebendo usuário e senha e inicializando as listas
     * vazias
     * 
     * @param usuario String
     * @param senha   String
     */
    public Cliente(String usuario, String senha) {
        this.nomeDeUsuario = usuario;
        this.login = usuario;
        this.senha = senha;
        this.listaParaVer = new HashMap<>();
        this.listaJaVistas = new HashMap<>();
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
        this.nomeDeUsuario = nome;
        this.login = usuario;
        this.senha = senha;
        this.listaParaVer = new HashMap<>();
        this.listaJaVistas = new HashMap<>();
    }

    public void adicionarAvaliacao(Audiovisual aud, double nota, String comentario) throws Exception {
        Avaliacao avaliacao = new Avaliacao(nota);
        IComentarista tipoEspecialista;
        aud.adicionarAvaliacao(this, avaliacao);

        try {
            tipoEspecialista = (IComentarista) tipo;
            tipoEspecialista.comentar(avaliacao, comentario);
        } catch (Exception e) {
            System.out.println("Cliente não é especialista, não é possivel comentar!");
        }
    }

    /**
     * Adiciona uma série em uma lista de séries para serem assistidas
     * 
     * @param audiovisual Audiovisual
     */
    public void adicionarNaLista(Audiovisual audiovisual) {
        listaParaVer.put(audiovisual.getId(), audiovisual);
    }

    /**
     * Adiciona uma série em uma lista de séries que já foram assistidas pelo
     * cliente
     *
     * @param audiovisual Audiovisual
     */
    public void adicionarNaListaJaVistas(Audiovisual audiovisual) throws Exception {

        if (audiovisual.getTipo() == "LANCAMENTO" && this.getTipo() != "Profissional")
            throw new IllegalArgumentException("Apenas clientes profissionais podem assistir lançamentos!");

        audiovisual.setDataAssistido();
        listaJaVistas.put(audiovisual.getId(), audiovisual);

        if (tipo == null && verificarEspecialista()) {
            System.out.println("Parabéns, você se tornou um cliente especialista!");
            tipo = EnumTipoCliente.Especialista;
        }
    }

    /**
     * Remove uma série em uma lista de séries para serem assistidas
     * 
     * @param nomeAudiovisual String
     */
    public void retirarDaLista(String nomeAudiovisual) {
        listaParaVer.values().removeIf(x -> x.getNome().equals(nomeAudiovisual));
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
        return String.format("%s;%s;%s", this.nomeDeUsuario, this.login, this.senha);
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

        return new Cliente(nome, login, senha);
    }

    /**
     * Método responsavel por verificar se um cliente e especialista
     * 
     * @return true se a lista de assistidos tiver mais de 5
     */
    private boolean verificarEspecialista() {
        LocalDate dataAtual = LocalDate.now();
        LocalDate dataLimite = dataAtual.minusMonths(1);
        int contador = 0;
        for (Audiovisual audiovisual : listaJaVistas.values()) {
            contador += (audiovisual.getDataAssistido().isAfter(dataLimite)
                    || audiovisual.getDataAssistido().isEqual(dataLimite)) ? 1 : 0;
        }
        return contador >= 5;
    }

    /**
     * Registra audiência a partir da classe Audiovisual
     * 
     * @param audiovisual Audiovisual
     */
    public void registrarAudiencia(Audiovisual audiovisual) {
        audiovisual.registrarAudiencia();
    }

    // #region getters
    public String getNomeUsuario() {
        return this.nomeDeUsuario;
    }

    public String getSenha() {
        return this.senha;
    }

    public String getLogin() {
        return this.login;
    }

    public String getTipo() {
        return this.tipo.name();
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
    // #endregion

    @Override
    public String toString() {
        String tipo;

        try {
            tipo = getTipo();
        } catch (Exception e) {
            tipo = "Regular";
        }

        return String.format("Bem vindo " + this.getNomeUsuario() + "! Seu login é "
                + this.getLogin() + " e sua senha é " + this.getSenha() + ".\n" + "Você é um cliente "
                + tipo);
    }
}
