package src;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.io.*;

public class PlataformaStreaming {
    private String nome;
    private HashSet<Serie> series;
    private HashSet<Cliente> clientes;
    private Cliente clienteAtual;

    PlataformaStreaming() {
        this.series = new HashSet<Serie>();
        this.clientes = new HashSet<Cliente>();
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
     * Adiciona uma série em uma lista de séries dentro da plataforma
     *
     * @param serie
     * @return boolean
     */
    public void adicionarSerie(Serie serie) {
        this.series.add(serie);
    }

    /**
     * Adiciona um cliente em uma lista de clientes dentro da plataforma
     * 
     * @param cliente
     * @return boolean
     */
    public void adicionarCliente(Cliente cliente) {
        this.clientes.add(cliente);
    }

    public void salvarCliente(Cliente cliente) throws IOException {
        try {
            // Fluxo de saida de um arquivo

            BufferedWriter br = new BufferedWriter(new FileWriter("codigo/src/files/POO_Espectadores.csv")); // adiciono a um escritor de buffer
            br.write(cliente.getNomeUsuario() + " " + cliente.getSenha() + ";\n"); // escrita no arquivo
            br.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

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
        Serie serieEncontrada = null;

        for (Serie serie : this.series)
            if (serie.getNome().equals(nomeSerie))
                serieEncontrada = serie;

        return serieEncontrada;
    }
}