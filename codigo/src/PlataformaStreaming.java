package src;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class PlataformaStreaming {
    private String nome;
    private HashSet<Serie> series;
    private HashSet<Cliente> clientes;
    private Cliente clienteAtual;

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
            if (cliente.getSenha() == senha && cliente.getNomeUsuario() == nomeUsuario)
                return cliente;

        return new Cliente();
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
                series.add((Serie) series);

        return series;
    }

    /**
     * Retorna uma lista de séries de acordo com o idioma informado
     * 
     * @param idioma
     * @return Lista de séries de um idioma específico
     */
    public List<Serie> filtrarPorIdioma(String idioma) {
        return new ArrayList<Serie>();
    }

    /**
     * Retorna uma lista de séries de acordo com a quantidade de episódeos informada
     * 
     * @param idioma
     * @return Lista de séries de uma determinada quantidade de episódeos
     */
    public List<Serie> filtrarPorQtdEpisodios(String idioma) {
        return new ArrayList<Serie>();
    }

    /**
     * -------------------
     * 
     * @param serie
     */
    public void registrarAudiencia(Serie serie) {

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
    public Serie buscaSerie(String nomeSerie) {
        return new Serie();
    }

}
