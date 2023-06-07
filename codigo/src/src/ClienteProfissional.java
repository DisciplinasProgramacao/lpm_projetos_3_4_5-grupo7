package src;

public class ClienteProfissional implements ICliente {
    public String descricao = "Profissional";

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
