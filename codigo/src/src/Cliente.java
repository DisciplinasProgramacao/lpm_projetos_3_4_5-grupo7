package src;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cliente implements IDAO<Cliente> {
    private String nomeDeUsuario;
    private String senha;
    private String login;
    private List<Audiovisual> listaParaVer;
    private List<Audiovisual> listaJaVistas;
    private ICliente tipo = new ClienteRegular();

    Cliente() {
    }

    /**
     * Construtor do cliente, recebendo usuário e senha e inicializando as listas
     * vazias
     * 
     * @param usuario
     * @param senha
     */
    public Cliente(String usuario, String senha) {
        this.nomeDeUsuario = usuario;
        this.senha = senha;
        this.listaParaVer = new ArrayList<>();
        this.listaJaVistas = new ArrayList<>();
    }

    /**
     * Construtor do cliente, recebendo usuário e senha e inicializando as listas
     * vazias
     * 
     * @param usuario
     * @param senha
     */
    public Cliente(String nome, String usuario, String senha) {
        this.nomeDeUsuario = nome;
        this.nomeDeUsuario = usuario;
        this.senha = senha;
        this.listaParaVer = new ArrayList<>();
        this.listaJaVistas = new ArrayList<>();
    }

    /**
     * Adiciona uma avaliação com comentário, caso o cliente seja Especialista
     * 
     * @param audiovisual
     * @param nota        (double)
     * @param comentario
     * @return boolean (se foi bem-sucedido ou não)
     */
    public boolean adicionarAvaliacao(Audiovisual aud, double nota, String comentario) {
        if (getEspecialista()) {
            Avaliacao avaliacao = new Avaliacao(nota, comentario);
            tipo.avaliar(this, aud, avaliacao);
            return true;
        } else {
            System.out.println("O Cliente não é especialista. Cadastre a avaliação novamente sem o comentário!");
            return false;
        }
    }

    /**
     * Adiciona uma avaliação sem comentário, para todo tipo de cliente.
     * 
     * @param audiovisual
     * @param nota        (double)
     * @return boolean (se foi bem-sucedido ou não)
     */
    public boolean adicionarAvaliacao(Audiovisual aud, double nota) {
        Avaliacao avaliacao = new Avaliacao(nota);
        tipo.avaliar(this, aud, avaliacao);
        return true;
    }

    /**
     * Adiciona uma série em uma lista de séries para serem assistidas
     * 
     * @param audiovisual
     */
    public void adicionarNaLista(Audiovisual audiovisual) {
        listaParaVer.add(audiovisual);
    }

    /**
     * Adiciona uma série em uma lista de séries que já foram assistidas pelo
     * cliente
     *
     * @param audiovisual
     */
    public void adicionarNaListaJaVistas(Audiovisual audiovisual) {
        audiovisual.setDataAssistido();
        listaJaVistas.add(audiovisual);
        if (verificarEspecialista()) {
            tipo = new ClienteEspecialista();
        }
    }

    /**
     * Remove uma série em uma lista de séries para serem assistidas
     * 
     * @param nomeAudiovisual
     */
    public void retirarDaLista(String nomeAudiovisual) {
        listaParaVer.removeIf(x -> x.getNome().equals(nomeAudiovisual));
    }

    /**
     * Junta duas listas retornando somente uma lista
     * 
     * @return Lista de séries
     */
    private List<Audiovisual> combinarListas() {
        List<Audiovisual> combinacaoListas = new ArrayList<Audiovisual>();
        combinacaoListas.addAll(this.listaParaVer);
        combinacaoListas.addAll(this.listaJaVistas);
        return combinacaoListas;
    }

    /**
     * Retorna uma lista de séries filtradas por gênero
     *
     * @param genero
     * @return Lista de séries filtradas por gênero
     */
    public List<Audiovisual> filtrarPorGenero(String genero) {
        List<Audiovisual> lista = new ArrayList<Audiovisual>();
        List<Audiovisual> combinacaoListas = combinarListas();

        for (Audiovisual audiovisual : combinacaoListas) {
            if (audiovisual.getGenero().equals(genero)) {
                lista.add(audiovisual);
            }
        }

        return lista;
    }

    /**
     * Retorna uma lista de séries filtradas por idioma
     * 
     * @param idioma
     * @return Lista de séries filtradas por idioma
     */
    public List<Audiovisual> filtrarPorIdioma(String idioma) {
        List<Audiovisual> lista = new ArrayList<Audiovisual>();
        List<Audiovisual> combinacaoListas = combinarListas();

        for (Audiovisual audiovisual : combinacaoListas) {
            if (audiovisual.getIdioma() == idioma)
                lista.add(audiovisual);
        }
        return lista;
    }

    /**
     * Implementação do método da interface: salva no arquivo de maneira formatada a
     * linha dos dados do cliente
     * 
     * @return String
     */
    @Override
    public String stringSalvar() {
        return String.format("%s;%s;%s", this.login, this.nomeDeUsuario, this.senha);
    }

    /**
     * Implementação do método da interface: carrega o obejto formatado com a
     * linha dos dados do cliente
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
        for (Audiovisual audiovisual : listaJaVistas) {
            contador += (audiovisual.getDataAssistido().isAfter(dataLimite) || audiovisual.getDataAssistido().isEqual(dataLimite)) ? 1 : 0;
        }
        return contador >= 5;
    }

    /**
     * Registra audiência a partir da classe Audiovisual
     * 
     * @param audiovisual
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

    public boolean getEspecialista() {
        return (tipo instanceof ClienteEspecialista);
    }
    // #endregion

}