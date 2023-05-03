package src;

import java.util.*;

public class Filtro implements IFiltro<Audiovisual> {
    @Override
    public List<Audiovisual> filtrarPorIdioma(Collection<Audiovisual> lista, String idioma) {
        List<Audiovisual> filtrada = new ArrayList<>();

        lista.forEach(item -> {
            if(item.getIdioma().equals(idioma)) {
                filtrada.add(item);
            }
        });

        return filtrada;
    }

    @Override
    public List<Audiovisual> filtrarPorGenero(Collection<Audiovisual> lista, String genero) {
        List<Audiovisual> filtrada = new ArrayList<>();

        lista.forEach(item -> {
            if(item.getGenero().equals(genero)) {
                filtrada.add(item);
            }
        });

        return filtrada;
    }

    public HashSet<Audiovisual> juntaHashSet(Collection<Serie> hs1, HashSet<Filme> hs2) {
        HashSet<Audiovisual> audiovisuais = new HashSet<>();

        for (Audiovisual serie : hs1) {
            for (Audiovisual filme : hs2) {
                audiovisuais.add(filme);
                audiovisuais.add(serie);
            }
        }

        return audiovisuais;
    }

    public boolean filtrar(Collection c, String a1, String a2) {
        return a1.equals(a2);
    }

    public boolean filtrar(Collection c, int a1, int a2) {
        return a1 == a2;
    }

    /*
   * public List filtrar(String metodo, String filtro) {
   * 
   * List<T> listaFiltrada = new ArrayList<T>();
   * for (T item : lista) {
   * if () {
   * listaFiltrada.add(item);
   * }
   * }
   * return listaFiltrada;
   * }
   */

}
