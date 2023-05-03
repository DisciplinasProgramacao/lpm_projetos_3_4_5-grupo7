package src;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.io.*;

public class PlataformaStreaming {
    private String nome;
    private HashSet<Audiovisual> audiovisuais;
    private HashSet<Cliente> clientes;
    private Cliente clienteAtual;

    PlataformaStreaming() {
        this.audiovisuais = new HashSet<Audiovisual>();
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
    public void adicionarAudioVisual(Audiovisual audiovisual) {
        this.audiovisuais.add(audiovisual);
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
    public List<Audiovisual> filtrarPorGenero(String genero) {
        List<Audiovisual> audiovisuais = new ArrayList<Audiovisual>();

        for (Audiovisual audiovisual : this.audiovisuais)
            if (audiovisual.getGenero() == genero)
                audiovisuais.add(audiovisual);

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

        for (Audiovisual audiovisual : this.audiovisuais)
            if (audiovisual.getIdioma() == idioma)
                audiovisuais.add(audiovisual);

        return audiovisuais;
    }

    /**
     * Retorna uma lista de séries de acordo com a quantidade de episódeos informada
     * 
     * @param quantEpisodios
     * @return Lista de séries de uma determinada quantidade de episódeos
     */
    public List<Serie> filtrarPorQtdEpisodios(int quantEpisodios) {
        List<Serie> audiovisuais = new ArrayList<Serie>();

        for (Serie Audiovisual : this.audiovisuais)
            if (Audiovisual.getQuantidadeEpisodios() == quantEpisodios)
                audiovisuais.add(Audiovisual);

        return audiovisuais;
    }

    /**
     * 
     * metodo responsavel por registrar audiencia de acordo com o
     * objeto Audiovisual passado
     * 
     * @param Audiovisual
     * 
     */
    public void registrarAudiencia(Audiovisual Audiovisual) {
        audiovisuais.stream().filter(x -> x.getNome() == Audiovisual.getNome()).findFirst().get().registrarAudiencia();
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

        for (Audiovisual Audiovisual : this.audiovisuais)
            if (Audiovisual.getNome().equals(nomeAudiovisual))
                AudiovisualEncontrada = Audiovisual;

        return AudiovisualEncontrada;
    }
}