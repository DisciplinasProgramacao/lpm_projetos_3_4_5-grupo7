package src;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class PlataformaStreaming<T> {
    private String nome;
    private HashSet<T> genericList;
    private HashSet<Cliente> clientes;
    private Cliente clienteAtual;

    PlataformaStreaming() {
        this.genericList = new HashSet<>();
        this.clientes = new HashSet<>();
        this.clienteAtual = null;
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
     * Adiciona uma instância do tipo T em uma lista de T dentro da plataforma
     *
     * @param t
     */
    public void adicionarSerie(T t) {
        this.genericList.add(t);
    }

    /**
     * Adiciona um cliente em uma lista de clientes dentro da plataforma
     * 
     * @param cliente
     */
    public void adicionarCliente(Cliente cliente) {
        this.clientes.add(cliente);
    }

    /**
     * Retorna uma lista de T de acordo com um gênero específico
     * 
     * @param genero
     * @return Lista de dados filtrados por genero
     */
    public List<T> filtrarPorGenero(String genero) {
        List<T> list = new ArrayList<>();

        for (T t : this.genericList)
            if (t.getGenero() == genero)
                list.add(t);

        return list;
    }

    /**
     * Retorna uma lista de T de acordo com o idioma informado
     * 
     * @param idioma
     * @return Lista de dados filtrados por idioma
     */
    public List<T> filtrarPorIdioma(String idioma) {
        List<T> list = new ArrayList<>();

        for (T t : this.genericList)
            if (list.getIdioma() == idioma)
                list.add(t);

        return list;
    }

    /**
     * Retorna uma lista de T de acordo com a quantidade de episódeos informada
     * 
     * @param quantEpisodios
     * @return Lista de dados filtrados por quantidade de episódeos
     */
    public List<T> filtrarPorQtdEpisodios(int quantEpisodios) {
        List<T> list = new ArrayList<>();

        for (T t : this.genericList)
            if (t.getQuantidadeEpisodios() == quantEpisodios)
                list.add(t);

        return list;
    }

    /**
     * 
     * Método responsável por registrar audiência de acordo com a referência t passada como argumento
     * 
     * @param t
     */
    public void registrarAudiencia(T t) {
        genericList.stream().filter(x -> x.getNome() == serie.getNome()).findFirst().get().registrarAudiencia();
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
     * @param nomeSerie
     * @return Retorna uma série específica
     */
    public T buscar(String nomeSerie) {
        T value = null;

        for (T t : this.genericList)
            if (t.getNome().equals(nomeSerie))
                value = t;

        return value;
    }
}
