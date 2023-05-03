package src;

public class Filme extends Audiovisual {
  int duracao;

  Filme(int id, String nome, String dataLancamento, int duracao) {
    super(id, nome, dataLancamento);
    setDuracao(duracao);
  }

  public int getDuracao() {
    return this.duracao;
  }

  public void setDuracao(int duracao) {
    this.duracao = duracao;
  }
}