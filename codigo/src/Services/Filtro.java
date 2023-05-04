package src.Services;

import src.Models.Audiovisual;

import java.util.*;

public class Filtro implements IFiltro<Audiovisual> {
    
    /** 
     * Método que filtra audiovisuais por idioma
     * @param lista
     * @param idioma
     * @return List<Audiovisual>
     */
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

    
    
    /** 
     * Método que filtra audiovisuais por gênero
     * @param lista
     * @param genero
     * @return List<Audiovisual>
     */
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
