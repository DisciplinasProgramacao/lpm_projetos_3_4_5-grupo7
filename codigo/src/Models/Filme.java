package src.Models;

import src.Services.IDAO;

public class Filme extends Audiovisual implements IDAO {
  int duracao;

  public Filme(int id, String nome, String dataLancamento, int duracao) {
    super(id, nome, dataLancamento);
    setDuracao(duracao);
  }

  @Override
  public String stringSalvar() {
    return String.format("%o;%s;%s;%o", this.getId(), this.getNome(), this.getDataLancamento(), this.getDuracao());
  }

  public int getDuracao() {
    return this.duracao;
  }

  public void setDuracao(int duracao) {
    this.duracao = duracao;
  }
}