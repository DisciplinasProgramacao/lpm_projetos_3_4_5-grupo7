package src;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nomeDeUsuario;
    private String senha;
    private List<Audiovisual> listaParaVer;
    private List<Audiovisual> listaJaVistas;

    public Cliente(String usuario, String senha) {
        this.nomeDeUsuario = usuario;
        this.senha = senha;
        this.listaParaVer = new ArrayList<>();
        this.listaJaVistas = new ArrayList<>();
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
        listaJaVistas.add(audiovisual);
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
     * Retorna uma lista de séries filtradas por quantidade de episódios
     * 
     * @param quantEpisodios
     * @return Lista de séries filtradas por quantidade de episódios
     */
    public List<Serie> filtrarPorQtdEpisodios(int quantEpisodios) {
        List<Serie> lista = new ArrayList<Serie>();
        List<Audiovisual> combinacaoListas = combinarListas();

        /*
         * for (Audiovisual audiovisual : combinacaoListas) {
         * if (audiovisual.getQuantidadeEpisodios() == quantEpisodios)
         * lista.add(audiovisual);
         * }
         */
        return lista;

    }

    /**
     * Registra audiência a partir da classe Audiovisual
     * 
     * @param audiovisual
     */
    public void registrarAudiencia(Audiovisual audiovisual) {
        audiovisual.registrarAudiencia();
    }

    // vai ter get e set de usuario e senha?
    public String getNomeUsuario() {
        return this.nomeDeUsuario;
    }

    public String getSenha() {
        return this.senha;
    }
}