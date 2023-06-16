package src;

public interface IComentarista {

  /**
   * Adiciona a avaliação no hash de avaliações do cliente
   *
   * @param cliente     Cliente
   * @param audiovisual Audiovisual
   * @param avaliacao   Avaliacao
   */
  void comentar(Avaliacao avaliacao, String comentario);
}