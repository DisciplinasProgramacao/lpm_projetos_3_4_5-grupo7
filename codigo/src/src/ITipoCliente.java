package src;

public interface ITipoCliente {

  /**
   * Adiciona a avaliação no hash de avaliações do cliente
   *
   * @param cliente     Cliente
   * @param audiovisual Audiovisual
   * @param avaliacao   Avaliacao
   */
  Avaliacao avaliar(double nota, String comentario);

  public String nomeTipo();
}
