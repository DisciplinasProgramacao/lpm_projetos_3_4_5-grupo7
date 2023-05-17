package src;

public class FiltroPersonalizado<T> {
  private T elemento;
  private String busca;

  public FiltroPersonalizado(T elemento, String busca) {
      this.elemento = elemento;
      this.busca = busca;
  }

  public T getElemento() {
      return elemento;
  }

  public String getBusca() {
      return busca;
  }
}