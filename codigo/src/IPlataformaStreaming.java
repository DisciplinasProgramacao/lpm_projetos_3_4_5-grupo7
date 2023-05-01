package src;

import java.util.List;

public interface IPlataformaStreaming<T extends Audiovisual> {
    void adicionar(T t);
    List<T> filtrarPorGenero(List<T> lista, String genero);
    List<T> filtrarPorIdioma(List<T> lista, String idioma);
    List<T> filtrarPorQtdEpisodios(List<T> lista, int quantEpisodios);
    public T buscar(String nome);
}
