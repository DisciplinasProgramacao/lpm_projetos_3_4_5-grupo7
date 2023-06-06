package src;

public interface IDAO<T> {
    /**
     * Reponsavel por trazer todos os dados para salvar
     * 
     * @return Retornar a linha que deseja salvar
     */
    String stringSalvar();

    /**
     * Reponsavel por carregar todos os dados do objeto
     * 
     * @param linha String
     * @return Retornar o objeto carregado
     */
    T loadObject(String linha);
}
