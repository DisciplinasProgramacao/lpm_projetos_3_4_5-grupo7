package src;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nomeDeUsuario;
    private String senha;
    private List<Serie> listaParaVer;
    private List<Serie> listaJaVistas;

    public Cliente(String usuario, String senha) {
        this.nomeDeUsuario = usuario;
        this.senha = senha;
        this.listaParaVer = new ArrayList<>();
        this.listaJaVistas = new ArrayList<>();
    }

    /**
     * Adiciona uma série em uma lista de séries para serem assistidas
     * 
     * @param serie
     */
    public void adicionarNaLista(Serie serie) {
        listaParaVer.add(serie);
    }

    /**
     * Remove uma série em uma lista de séries para serem assistidas
     * 
     * @param nomeSerie
     */
    public void retirarDaLista(String nomeSerie) {
        listaParaVer.removeIf(x -> x.getNome().equals(nomeSerie));
    }

    /**
     * Junta duas listas retornando somente uma lista
     * 
     * @return Lista de séries
     */
    private List<Serie> combinarListas() {
        List<Serie> combinacaoListas = new ArrayList<Serie>();
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
    public List<Serie> filtrarPorGenero(String genero) {
        List<Serie> lista = new ArrayList<Serie>();
        List<Serie> combinacaoListas = combinarListas();

        for (Serie serie : combinacaoListas) {
            if (serie.getGenero().equals(genero)) {
                lista.add(serie);
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
    public List<Serie> filtrarPorIdioma(String idioma) {
        List<Serie> lista = new ArrayList<Serie>();
        List<Serie> combinacaoListas = combinarListas();

        for (Serie serie : combinacaoListas) {
            if (serie.getIdioma().equals(idioma)) {
                lista.add(serie);
            }
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
        List<Serie> combinacaoListas = combinarListas();

        for (Serie serie : combinacaoListas) {
            if (serie.getQuantidadeEpisodios() == quantEpisodios) {
                lista.add(serie);
            }
        }

        return lista;
    }

    /**
     * Registra audiência a partir da classe Serie
     * 
     * @param serie
     */
    public void registrarAudiencia(Serie serie) {
        serie.registrarAudiencia();
    }

    // vai ter get e set de usuario e senha?
    public String getNomeUsuario() {
        return this.nomeDeUsuario;
    }

    public String getSenha() {
        return this.senha;
    }
}