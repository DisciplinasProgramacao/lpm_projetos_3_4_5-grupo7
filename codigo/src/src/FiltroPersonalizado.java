package src;

public class FiltroPersonalizado<T> {
  private final T elemento;
  private final String busca;

  /**
   * Construtor de um filtro personalizado, recebe um elemento de qualquer tipo 
   * e uma string que servirá posteriormente como parâmetro do filtro.
   * @param elemento T
   * @param busca String
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