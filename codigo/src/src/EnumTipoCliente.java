package src;

public enum EnumTipoCliente implements IComentarista{
  Especialista,
  Profissional;

  public void comentar(Avaliacao avaliacao,String comentario) {
        avaliacao.comentar(comentario);
    }
}
