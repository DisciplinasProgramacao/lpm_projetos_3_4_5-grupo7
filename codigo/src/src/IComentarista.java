package src;

public interface IComentarista {

  /**
   * Adiciona a avaliação no hash de avaliações do cliente
   *
   * @param avaliacao  Avaliacao
   * @param comentario String
   */
  void comentar(Avaliacao avaliacao, String comentario);
}