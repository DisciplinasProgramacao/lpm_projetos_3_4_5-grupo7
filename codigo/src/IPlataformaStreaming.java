package src;

import java.util.List;

public interface IPlataformaStreaming<T extends Audiovisual> {
    void adicionar(T t);
    List<T> filtrarPorGenero(String genero);
    List<T> filtrarPorIdioma(String idioma);
    List<T> filtrarPorQtdEpisodios(int quantEpisodios);
    public T buscar(String nome);
}
