package src;

import java.util.Random;

public class Serie extends Audiovisual implements IDAO<Serie> {
    private static int[] QUANTIDADEEPISODIOS;
    private int quantidadeEpisodios;

    public Serie() {
        super();
        Random randomSerie = new Random();
        QUANTIDADEEPISODIOS = new int[] { 4, 5, 8, 10, 12 };
        this.quantidadeEpisodios = (QUANTIDADEEPISODIOS[(randomSerie.nextInt(5))]);
    }

    /**
     * Construtor da src.Serie - recebe as informações báiscas da série presentes no
     * arquivo;
     *
     * @param id             int
     * @param nome           String
     * @param dataLancamento String
     */
    public Serie(int id, String nome, String dataLancamento) {
        super(id, nome, dataLancamento);
        init();
    }

    public Serie(int id, String nome, String dataLancamento, Tipo tipo) {
        super(id, nome, dataLancamento, tipo);
        init();
    }

    public void init() {
        Random randomSerie = new Random();
        QUANTIDADEEPISODIOS = new int[] { 4, 5, 8, 10, 12 };
        this.quantidadeEpisodios = (QUANTIDADEEPISODIOS[(randomSerie.nextInt(5))]);
    }

    @Override
    public String toString() {
        return String.format(
                "\nId: %d\nNome: %s\nData de Lançamento: %s\nAvaliação: %s\nGênero: %s\nIdioma: %s\nStreams: %d\nQuantidade de EPs: %d\nTipo: %s\n",
                this.id, this.nome, this.dataLancamento, gerarMediaAvaliacoes(), this.genero,
                this.idioma, this.audiencia, this.quantidadeEpisodios, this.tipo.toString());
    }

    /**
     * Implementação da DAO - salva a série formatada no arquivo POO_Series.cvs
     */
    @Override
    public String stringSalvar() {
        char tipo = this.getTipo().charAt(0);
        return String.format("%d;%s;%s;%c", this.id, this.nome, this.dataLancamento, tipo);
    }

    /**
     * Implementação do método da interface: carrega o obejto formatado com a
     * linha dos dados do filme
     */
    @Override
    public Serie loadObject(String linha) {
        String[] dados = linha.split(";");

        int id = Integer.parseInt(dados[0].trim().replaceAll("\\p{C}", ""));
        String nome = dados[1].trim();
        String dataLancamento = dados[2].trim();
        Tipo tipo = Tipo.REGULAR;

        switch (dados[3].trim()) {
            case "P":
                tipo = Tipo.PRELANCAMENTO;
                break;
            default:
                tipo = Tipo.REGULAR;
                break;
        }
        return new Serie(id, nome, dataLancamento, tipo);
    }

    public int getQuantidadeEpisodios() {
        return quantidadeEpisodios;
    }

}