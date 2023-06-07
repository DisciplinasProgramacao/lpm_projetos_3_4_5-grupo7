package src;

public class ClienteEspecialista implements ICliente {
  private String descricao = "Especialista";

  /**
   * Adiciona a avaliação no hash de avaliações do cliente
   *
   * @param cliente     Cliente
   * @param audiovisual Audiovisual
   * @param avaliacao   Avaliacao
   */
  public void avaliar(Cliente cliente, Audiovisual audiovisual, Avaliacao avaliacao) {
    audiovisual.adicionarAvaliacao(cliente, avaliacao);
  }
  
  public String getDescricao() {
    return descricao;
  }
}
