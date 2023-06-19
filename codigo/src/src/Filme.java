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
    this.duracao = (duracao);
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
    this.duracao = (duracao);
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
    char tipo = getTipo().charAt(0);
    return String.format("%d;%s;%s;%o;%c", this.id, this.nome, this.dataLancamento, this.duracao,
        tipo);
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
    Tipo tipo = Tipo.REGULAR;

    switch (dados[4].trim()) {
      case "P":
        tipo = Tipo.PRELANCAMENTO;
        break;
      default:
        tipo = Tipo.REGULAR;
        break;
    }
    return new Filme(id, nome, dataLancamento, duracao, tipo);
  }

}