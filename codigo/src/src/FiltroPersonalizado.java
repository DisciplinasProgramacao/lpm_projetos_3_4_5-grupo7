package src;

public class FiltroPersonalizado<T> {
  private T elemento;
  private String busca;

  /**
   * Construtor de um filtro personalizado, recebe um elemento de qualquer tipo 
   * e uma string que servirá posteriormente como parâmetro do filtro.
   * @param elemento
   * @param busca
   */
  public FiltroPersonalizado(T elemento, String busca) {
      this.elemento = elemento;
      this.busca = busca;
  }

  //#region get/set
  public T getElemento() {
      return elemento;
  }

  public String getBusca() {
      return busca;
  }
  //#endregion
}