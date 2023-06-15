package src;

public class ClienteProfissional implements IComentarista {
    public String descricao = "Profissional";

    /**
     * Adiciona a avaliação no hash de avaliações do cliente
     *
     * @param cliente     Cliente
     * @param audiovisual Audiovisual
     * @param avaliacao   Avaliacao
     */
    public void comentar(Avaliacao avaliacao,String comentario) {
        avaliacao.comentar(comentario);
    }

    public String getDescricao() {
        return descricao;
    }
}
