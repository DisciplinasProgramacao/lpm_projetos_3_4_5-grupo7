package src;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
   private String nomeDeUsuario;
   private String senha;
   private List<Serie> listaParaVer;
   private List<Serie> listaJaVistas;

    Cliente(){
        this.listaParaVer = new ArrayList<Serie>();
        this.listaJaVistas = new ArrayList<Serie>();
    }
    public void adicionarNaLista(Serie serie) {
        listaParaVer.add(serie);
    }

    public void retirarDaLista(String nomeSerie) {
        listaParaVer.removeIf(x-> x.getNome().equals(nomeSerie));
    }

    private List<Serie> combinarListas(){
        List<Serie> combinacaoListas = new ArrayList<Serie>();
        combinacaoListas.addAll(this.listaParaVer);
        combinacaoListas.addAll(this.listaJaVistas);
        return combinacaoListas; 
    }

    public List<Serie> filtrarPorGenero(String genero){
        List<Serie> lista = new ArrayList<Serie>();
        List<Serie> combinacaoListas = combinarListas();

        for (Serie serie : combinacaoListas) {
            if(serie.getGenero().equals(genero)){
                lista.add(serie);
            }
        }

        return lista;
    }

    public List<Serie> filtrarPorIdioma(String idioma){
        List<Serie> lista = new ArrayList<Serie>();
        List<Serie> combinacaoListas = combinarListas();

        for (Serie serie : combinacaoListas) {
            if(serie.getIdioma().equals(idioma)){
                lista.add(serie);
            }
        }

        return lista;
    }

    public List<Serie> filtrarPorQtdEpisodios(int quantEpisodios){
        List<Serie> lista = new ArrayList<Serie>();
        List<Serie> combinacaoListas = combinarListas();

        for (Serie serie : combinacaoListas) {
            if(serie.getQuantidadeEpisodios() == quantEpisodios){
                lista.add(serie);
            }
        }

        return lista;
    }

    public void registrarAudiencia(Serie serie){
        serie.registrarAudiencia();
    }


   
}