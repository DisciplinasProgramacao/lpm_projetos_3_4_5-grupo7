package src;

public class ClienteEspecialista implements IComentarista {
  private String descricao = "Especialista";

  public void comentar(Avaliacao avaliacao,String comentario) {
        avaliacao.comentar(comentario);
    }
      
  public String getDescricao() {
    return descricao;
  }
}
