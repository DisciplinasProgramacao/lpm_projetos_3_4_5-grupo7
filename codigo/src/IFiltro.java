import java.util.Collection;
import java.util.List;

/**
 * Interface que aplica os filtros de idioma e gênero
 */
public interface IFiltro<T> {
    /**
     * Método que filtra por idioma
     * 
     * @param lista
     * @param idioma
     * @return List<T>
     */
    List<T> filtrarPorIdioma(Collection<T> lista, String idioma);

    /**
     * Método que filtra por gênero
     * 
     * @param lista
     * @param genero
     * @return
     */
    List<T> filtrarPorGenero(Collection<T> lista, String genero);
}
