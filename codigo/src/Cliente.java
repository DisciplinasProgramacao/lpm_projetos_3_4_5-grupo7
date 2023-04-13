package src;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    String nomeDeUsuario;
    String senha;
    List<Serie> listaParaVer;
    List<Serie> listaJaVistas;

    Cliente(){
        this.listaParaVer = new ArrayList<Serie>();
        this.listaJaVistas = new ArrayList<Serie>();
    }
    public void adicionarNaLista(Serie serie) {
        listaParaVer.add(serie);
    }

    public void retirarDaLista(String nomeSerie) {
        listaParaVer.remove(nomeSerie);
    }


    public List<Serie> filtrarPorGenero(String genero){
        List<Serie> lista = new ArrayList<Serie>();
        List<Serie> combinacaoListas = new ArrayList<Serie>();
        combinacaoListas.addAll(this.listaParaVer);
        combinacaoListas.addAll(this.listaJaVistas);


        for (Serie serie : combinacaoListas) {
            if(serie.getGenero().equals(genero)){
                lista.add(serie);
            }
        }

        return lista;
    }

    

}