package src;

import java.util.*;
import java.util.function.Predicate;

public class Filtro<T> {
    
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
