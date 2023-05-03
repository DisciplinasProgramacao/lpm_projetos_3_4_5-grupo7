package src;

import java.util.Collection;
import java.util.List;

public interface IFiltro<T> {
    List<T> filtrarPorIdioma(Collection<T> lista, String idioma);
    List<T> filtrarPorGenero(Collection<T> lista, String genero);
}
