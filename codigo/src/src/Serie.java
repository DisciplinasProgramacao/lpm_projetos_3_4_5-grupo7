package src;

import java.util.Random;

public class Serie extends Audiovisual implements IDAO<Serie> {
    private static int[] QUANTIDADEEPISODIOS;
    private int quantidadeEpisodios;

    public Serie() {
        super();
        Random randomSerie = new Random();
        QUANTIDADEEPISODIOS = new int[] { 4, 5, 8, 10, 12};
        setQuantidadeEpisodios(QUANTIDADEEPISODIOS[(randomSerie.nextInt(5))]);
    }
    /**
     * Construtor da src.Serie - recebe as informações báiscas da série presentes no
     * arquivo;
     *
     * @param id int
     * @param nome String
     * @param dataLancamento String
     */
    public Serie(int id, String nome, String dataLancamento) {
        super(id, nome, dataLancamento);
        Random randomSerie = new Random();
        QUANTIDADEEPISODIOS = new int[] { 4, 5, 8, 10, 12};
        setQuantidadeEpisodios(QUANTIDADEEPISODIOS[(randomSerie.nextInt(5))]);
    }

    /**
     * Implementação da DAO - salva a série formatada no arquivo POO_Series.cvs
     */
    @Override
    public String stringSalvar() {
        return String.format("%o;%s;%s", this.getId(), this.getNome(), this.getDataLancamento());
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

        return new Serie(id, nome, dataLancamento);
    }

    // #region get/set
    public int getQuantidadeEpisodios() {
        return quantidadeEpisodios;
    }

    public void setQuantidadeEpisodios(int quantidadeEpisodios) {
        this.quantidadeEpisodios = quantidadeEpisodios;
    }
    // #endregion
}