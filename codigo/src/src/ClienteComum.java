package src;

public class ClienteComum implements ITipoCliente{
  private String nome = "Comum";

  /**
   * Adiciona a avaliação no hash de avaliações do cliente
   *
   * @param cliente     Cliente
   * @param audiovisual Audiovisual
   * @param avaliacao   Avaliacao
   */
  public Avaliacao avaliar(double nota, String comentario) {
        Avaliacao avaliacao = new Avaliacao(nota);
        System.out.println("Avaliação registrada sem comentário, cliente não pode comentar");

        return avaliacao;
    }

    public String nomeTipo(){
      return this.nome;
    }
}
