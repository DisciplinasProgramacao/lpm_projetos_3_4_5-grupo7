package src;

public class Filme extends Audiovisual implements IDAO<Filme> {
  private int duracao;

  /**
   * Construtor do filme - recebe suas informações básicas presentes no arquivo
   *
   * @param id             int
   * @param nome           String
   * @param dataLancamento String
   * @param duracao        int
   */
  public Filme(int id, String nome, String dataLancamento, int duracao) {
    super(id, nome, dataLancamento);
    setDuracao(duracao);
  }

  /**
   * Construtor do filme - cria um filme recebendo um tipo
   *
   * @param id             int
   * @param nome           String
   * @param dataLancamento String
   * @param duracao        int
   */
  public Filme(int id, String nome, String dataLancamento, int duracao, Tipo tipo) {
    super(id, nome, dataLancamento, tipo);
    setDuracao(duracao);
  }

  public Filme() {
    super();
  }

  /**
   * Implementação do método da interface: salva no arquivo de maneira formatada a
   * linha dos dados do filme
   */
  @Override
  public String stringSalvar() {
    return String.format("%o;%s;%s;%o", this.getId(), this.getNome(), this.getDataLancamento(), this.getDuracao());
  }

  /**
   * Implementação do método da interface: carrega o obejto formatado com a
   * linha dos dados do filme
   *
   * @param linha String
   */
  @Override
  public Filme loadObject(String linha) {
    String[] dados = linha.split(";");

    int id = Integer.parseInt(dados[0].trim().replaceAll("\\p{C}", ""));
    String nome = dados[1].trim();
    String dataLancamento = dados[2].trim();
    int duracao = Integer.parseInt(dados[3].trim());

    return new Filme(id, nome, dataLancamento, duracao);
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