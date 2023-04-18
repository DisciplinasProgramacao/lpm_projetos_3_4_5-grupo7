package src;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class PlataformaStreaming {
    private String nome;
    private HashSet<Serie> series;
    private HashSet<Cliente> clientes;
    private Cliente clienteAtual;

    PlataformaStreaming() {
        this.series = new HashSet<Serie>();
        this.clientes = new HashSet<Cliente>();
        this.clienteAtual = new Cliente();
    }

    // #region Get Set
    public Cliente getcClienteAtual() {
        return this.clienteAtual;
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
     * @param serie
     * @return boolean
     */
    public boolean adicionarSerie(Serie serie) {
        Boolean adicionouSerie = this.series.add(serie);
        return adicionouSerie;
    }

    /**
     * Adiciona um cliente em uma lista de clientes dentro da plataforma
     * 
     * @param cliente
     * @return boolean
     */
    public boolean adicionarCliente(Cliente cliente) {
        Boolean adicionouCliente = this.clientes.add(cliente);
        return adicionouCliente;
    }

    /**
     * Retorna uma lista de séries de acordo com um gênero específico
     * 
     * @param genero
     * @return Lista de séries de um gênero específico
     */
    public List<Serie> filtrarPorGenero(String genero) {
        List<Serie> series = new ArrayList<Serie>();

        for (Serie serie : this.series)
            if (serie.getGenero() == genero)
                series.add(serie);

        return series;
    }

    /**
     * Retorna uma lista de séries de acordo com o idioma informado
     * 
     * @param idioma
     * @return Lista de séries de um idioma específico
     */
    public List<Serie> filtrarPorIdioma(String idioma) {
        List<Serie> series = new ArrayList<Serie>();

        for (Serie serie : this.series)
            if (serie.getIdioma() == idioma)
                series.add(serie);

        return series;
    }

    /**
     * Retorna uma lista de séries de acordo com a quantidade de episódeos informada
     * 
     * @param quantEpisodios
     * @return Lista de séries de uma determinada quantidade de episódeos
     */
    public List<Serie> filtrarPorQtdEpisodios(int quantEpisodios) {
        List<Serie> series = new ArrayList<Serie>();

        for (Serie serie : this.series)
            if (serie.getQuantidadeEpisodios() == quantEpisodios)
                series.add(serie);

        return series;
    }

    /**
     * 
     * metodo responsavel por registrar audiencia de acordo com o
     * objeto serie passado
     * 
     * @param serie
     * 
     */
    public void registrarAudiencia(Serie serie) {
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
     * @param nomeSerie
     * @return Retorna uma série específica
     */
    public Serie buscarSerie(String nomeSerie) {
        Serie serieEncontrada = new Serie();

        for (Serie serie : this.series)
            if (serie.getNome() == nomeSerie)
                serieEncontrada = serie;

        return serieEncontrada;
    }

}
