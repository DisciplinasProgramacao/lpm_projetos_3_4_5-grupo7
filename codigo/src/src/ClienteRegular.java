package src;

public class ClienteRegular implements ICliente {
  public void avaliar(Cliente cliente, Audiovisual audiovisual, Avaliacao avaliacao) {
    audiovisual.adicionarAvaliacao(cliente, avaliacao);
  }
}
