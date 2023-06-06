package src;

import java.util.*;
import java.util.function.Predicate;

public class Filtro<T> {
    /**
     * Recebe um filtro por parâmetro e o utiliza para filtrar uma lista a partir de uma string
     * @param lista List<T>
     * @param filtro Predicate<FiltroPersonalizado<T>>
     * @param busca String
     * @return List<T>
     */
    public List<T> filtrar(List<T> lista, Predicate<FiltroPersonalizado<T>> filtro, String busca) {
        List<T> resultado = new ArrayList<>();
        for (T elemento : lista) {
            FiltroPersonalizado<T> filtroPersonalizado = new FiltroPersonalizado<>(elemento, busca);
            if (filtro.test(filtroPersonalizado)) {
                resultado.add(elemento);
            }
        }
        return resultado;
    }
}
