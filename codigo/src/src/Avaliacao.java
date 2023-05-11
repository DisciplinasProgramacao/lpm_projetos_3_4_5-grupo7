package src;

import java.util.Stack;

public class Avaliacao {
    private static final Double avaliacaoMaxima = 5.0;

    private Stack<Double> avaliacoes;

    public Avaliacao() {
        this.avaliacoes = new Stack<>();
    }

    public Stack<Double> getAvaliacoes() {
        return (Stack<Double>) avaliacoes.clone();
    }

    /**
     * Método responsável por cadastrar uma nova avaliação
     * 
     * @param nota
     */
    public void cadastrarAvaliacao(double nota) {
        if (nota >= 1 && nota <= avaliacaoMaxima)
            this.avaliacoes.add(nota);
    }

    /**
     * Método responsável por gerar a média das avaliações
     * 
     * @return double
     */
    public double gerarMedia() {
        return this.avaliacoes.stream().mapToDouble(num -> num).average().getAsDouble();
    }
}