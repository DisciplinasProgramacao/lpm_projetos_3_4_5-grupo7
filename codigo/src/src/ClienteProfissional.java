package src;

public class ClienteProfissional implements ITipoCliente {
    public String nome = "Profissional";

    /**
     * Adiciona a avaliação no hash de avaliações do cliente
     *
     * @param nota    Cliente
     * @param comentario
     * @return avaliacao
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
