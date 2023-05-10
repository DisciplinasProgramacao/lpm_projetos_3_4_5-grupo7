package src;

public class Filme extends Audiovisual implements IDAO {
  int duracao;

  /**
   * Construtor do filme - recebe suas informações básicas presentes no arquivo
   * 
   * @param id
   * @param nome
   * @param dataLancamento
   * @param duracao
   */
  public Filme(int id, String nome, String dataLancamento, int duracao) {
    super(id, nome, dataLancamento);
    setDuracao(duracao);
  }

  /**
   * Implementação do método da interface: salva no arquivo de maneira formatada a
   * linha dos dados do filme
   */
  @Override
  public String stringSalvar() {
    return String.format("%o;%s;%s;%o", this.getId(), this.getNome(), this.getDataLancamento(), this.getDuracao());
  }

  // #region get/set
  public int getDuracao() {
    return this.duracao;
  }

  public void setDuracao(int duracao) {
    this.duracao = duracao;
  }
  // #endregion
}