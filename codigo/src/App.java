package src;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        String arquivoSerie = "codigo/POO_Series_2023/POO_Series.csv";
        String arquivoAudiencia = "lpm_projetos_3_4_5-grupo7/codigo/src/files/POO_Audiencia.csv";
        String arquivoEspectadores = "codigo/POO_Series_2023/POO_Espectadores.csv";
        DadosService meusDados = new DadosService();
        meusDados.carregarDados(arquivoSerie);
        DadosService clientes = new DadosService();
        clientes.geraListaCliente(arquivoEspectadores);
        DadosService audiencia = new DadosService();
        audiencia.geraListaAudiencia(arquivoAudiencia);

    }
}
