package src;

public class ClienteEspecialista implements ICliente {
/**
 * Adiciona a avaliação no hash de avaliações do
 * @param Cliente
 * @param Audiovisual
 * @param Avaliacao
 */
  public void avaliar(Cliente cliente, Audiovisual audiovisual, Avaliacao avaliacao) {
    audiovisual.adicionarAvaliacao(cliente, avaliacao);
  }
}
