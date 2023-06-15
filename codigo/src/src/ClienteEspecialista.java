package src;

public class ClienteEspecialista implements ITipoCliente {
  private String nome = "Especialista";

  /**
   * Adiciona a avaliação no hash de avaliações do cliente
   *
   * @param cliente     Cliente
   * @param audiovisual Audiovisual
   * @param avaliacao   Avaliacao
   */
  public Avaliacao avaliar(double nota, String comentario) {
        Avaliacao avaliacao = new Avaliacao(nota, comentario);
        System.out.println("Avaliação registrada com Sucesso");

        return avaliacao;
    }

  public String nomeTipo(){
      return this.nome;
    }
}
