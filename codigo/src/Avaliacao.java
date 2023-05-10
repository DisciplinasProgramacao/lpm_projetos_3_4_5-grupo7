import java.util.List;
import java.util.Stack;

public class Avaliacao {
    private static final Double avaliacaoMaxima = 5.0;

    private List<Double> avaliacoes;

    Avaliacao() {
        this.avaliacoes = new Stack<Double>();
    }

    /**
     * Metodo responsavel por cadastrar uma nova avaliação
     * 
     * @param nota
     */
    public void cadastrarAvaliacao(double nota) {
        if (nota >= 1 && nota <= avaliacaoMaxima)
            this.avaliacoes.add(nota);
    }

    /**
     * metodo responsavel por gerar a media das avaliações
     * 
     * @return
     */
    public double gerarMedia() {
        double total = this.avaliacoes.parallelStream().reduce(0.0, (subtotal, valor) -> subtotal + valor);
        int tamanho = this.avaliacoes.size();
        return tamanho > 0 ? total / tamanho : 0;
    }

}