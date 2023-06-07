package src;

public interface ICliente {
  public String getDescricao();

  /**
   * Adiciona a avaliação no hash de avaliações do cliente
   *
   * @param cliente     Cliente
   * @param audiovisual Audiovisual
   * @param avaliacao   Avaliacao
   */
  void avaliar(Cliente cliente, Audiovisual audiovisual, Avaliacao avaliacao);
}
