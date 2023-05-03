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

}
