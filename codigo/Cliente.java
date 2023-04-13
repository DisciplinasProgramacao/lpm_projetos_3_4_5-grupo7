package codigo;

import java.util.List;

public class Cliente {
    String nomeDeUsuario;
    String senha;
    List<Serie> listaParaVer;
    List<Serie> listaJaVistas;

    public void adicionarNaLista(Serie serie){
        listaParaVer.add(serie);
    }

    public void retirarDaLista(String nomeSerie){
        listaParaVer.remove(nomeSerie);
    }

}