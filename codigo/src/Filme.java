public class Filme extends Audiovisual {
  int duracao;

  Filme(String nome){
    super(nome);
  }

  
public int getDuracao() {
    return this.duracao;
}

public void setQuantidadeEpisodios(int duracao) {
    this.duracao = duracao;
}
}