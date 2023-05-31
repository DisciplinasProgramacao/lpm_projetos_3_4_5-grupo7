package src;

public interface IDAO<T> {
    /**
     * Reponsavel por trazer todos os dados para salvar
     * 
     * @return Retornar a linha que deseja salvar
     */
    public String stringSalvar();

    /**
     * Reponsavel por carregar todos os dados do objeto
     * 
     * @param String
     * @return Retornar o objeto carregado
     */
    public T loadObject(String linha);
}
