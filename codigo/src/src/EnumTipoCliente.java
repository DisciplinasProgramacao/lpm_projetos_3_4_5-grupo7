package src;

public enum EnumTipoCliente implements IComentarista {
  ESPECIALISTA,
  PROFISSIONAL;

  public void comentar(Avaliacao avaliacao, String comentario) {
    avaliacao.comentar(comentario);
  }
}
