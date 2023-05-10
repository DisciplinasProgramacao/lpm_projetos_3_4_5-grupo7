package src;

public class Serie extends Audiovisual implements IDAO {
    private int quantidadeEpisodios;

    /**
     * Construtor da src.Serie - recebe as informações báiscas da série presentes no
     * arquivo;
     * 
     * @param id
     * @param nome
     * @param dataLancamento
     */
    public Serie(int id, String nome, String dataLancamento) {
        super(id, nome, dataLancamento);
    }

    /**
     * Implementação da DAO - salva a série formatada no arquivo POO_Series.cvs
     */
    @Override
    public String stringSalvar() {
        return String.format("%o;%s;%s", this.getId(), this.getNome(), this.getDataLancamento());
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