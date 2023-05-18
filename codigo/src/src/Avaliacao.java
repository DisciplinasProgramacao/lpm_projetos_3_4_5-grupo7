package src;


public class Avaliacao {
    private static final Double avaliacaoMaxima = 5.0;

    private Double nota;
    private String comentario;

    public Avaliacao(Double nota) {
        cadastrarAvaliacao(nota);
    }

    public Avaliacao(Double nota, String comentario) {
        cadastrarAvaliacao(nota);
        this.comentario = comentario;

    }

    public Double getNota() {
        return this.nota;
    }

    public String getComentario() {
        return this.comentario;
    }

    /**
     * Método responsável por cadastrar uma nova avaliação
     * 
     * @param nota
     */
    private void cadastrarAvaliacao(double nota) {
        this.nota = (nota >= 1 && nota <= avaliacaoMaxima) ? nota : 0;
    }

    @Override
    public String toString() {
        return "Nota: " + this.nota + " - Comentário: " + this.comentario;
    }
}