package src;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.io.*;

public class PlataformaStreaming {
    private String nome;
    private HashSet<Serie> series;
    private HashSet<Filme> filmes;
    private HashSet<Cliente> clientes;
    private Cliente clienteAtual;

    PlataformaStreaming() {
        this.series = new HashSet<Serie>();
        this.filmes = new HashSet<Filme>();
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
     * @param Audiovisual
     * @return boolean
     */
    public void adicionarFilme(Filme filme) {
        this.filmes.add(filme);
    }

    /**
     * Adiciona uma série em uma lista de séries dentro da plataforma
     *
     * @param Audiovisual
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

            // adiciona um escritor de buffer
            BufferedWriter br = new BufferedWriter(new FileWriter("codigo/src/files/POO_Espectadores.csv"));

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
    public List<Audiovisual> filtrarPorGenero(String genero) {
        List<Audiovisual> audiovisuais = new ArrayList<Audiovisual>();

        for (Audiovisual serie : this.series) {
            for (Audiovisual filme : this.filmes) {
                if (filme.getGenero() == genero)
                    audiovisuais.add(filme);
                if (serie.getGenero() == genero)
                    audiovisuais.add(serie);
            }
        }

        return audiovisuais;
    }

    /**
     * Retorna uma lista de séries de acordo com o idioma informado
     * 
     * @param idioma
     * @return Lista de séries de um idioma específico
     */
    public List<Audiovisual> filtrarPorIdioma(String idioma) {
        List<Audiovisual> audiovisuais = new ArrayList<Audiovisual>();

        for (Audiovisual serie : this.series) {
            for (Audiovisual filme : this.filmes) {
                if (filme.getIdioma() == idioma)
                    audiovisuais.add(filme);
                if (serie.getIdioma() == idioma)
                    audiovisuais.add(serie);
            }
        }
        return audiovisuais;
    }

    /**
     * Retorna uma lista de séries de acordo com a quantidade de episódeos informada
     * 
     * @param quantEpisodios
     * @return Lista de séries de uma determinada quantidade de episódeos
     */
    public List<Serie> filtrarPorQtdEpisodios(int quantEpisodios) {
        List<Serie> seriesFiltradas = new ArrayList<Serie>();

        for (Serie serie : this.series)
            if (serie.getQuantidadeEpisodios() == quantEpisodios)
                seriesFiltradas.add(serie);

        return seriesFiltradas;
    }

    /**
     * 
     * metodo responsavel por registrar audiencia de acordo com o
     * objeto Audiovisual passado
     * 
     * @param Audiovisual
     * 
     */
    public void registrarAudienciaFilme(Filme filme) {
        filmes.stream().filter(x -> x.getNome() == filme.getNome()).findFirst().get().registrarAudiencia();
    }

    public void registrarAudienciaSerie(Serie serie) {
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
     * @param nomeAudiovisual
     * @return Retorna uma série específica
     */
    public Audiovisual buscarAudiovisual(String nomeAudiovisual) {
        Audiovisual AudiovisualEncontrada = null;

        for (Audiovisual filme : this.filmes) {
            for (Audiovisual serie : this.series) {
                if (filme.getNome().equals(nomeAudiovisual)) {
                    AudiovisualEncontrada = filme;
                } else if (serie.getNome().equals(nomeAudiovisual)) {
                    AudiovisualEncontrada = serie;
                }

            }

        }

        return AudiovisualEncontrada;
    }

}