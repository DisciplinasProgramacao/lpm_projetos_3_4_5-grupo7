package src;

import java.util.List;

public interface IDAO {
    /**
     * Reponsavel por trazer todos os dados para salvar
     * 
     * @return Retornar uma lista com as linhas que deseja salvar 
     */
    public List<String> stringSalvar();
}
