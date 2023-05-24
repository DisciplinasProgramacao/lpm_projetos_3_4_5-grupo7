package src;

public class ClienteEspecialista implements ICliente {
  public void avaliar(Cliente cliente, Audiovisual audiovisual, Avaliacao avaliacao) {
    audiovisual.adicionarAvaliacao(cliente, avaliacao);
  }
}
