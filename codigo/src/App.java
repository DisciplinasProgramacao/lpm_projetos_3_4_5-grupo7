package src;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        PlataformaStreaming ps = new PlataformaStreaming();
        DadosService meusDados = new DadosService(ps);

        meusDados.show();
//        DadosService clientes = new DadosService();
//        clientes.geraListaCliente(arquivoEspectadores);
//        DadosService audiencia = new DadosService();
//        audiencia.geraListaAudiencia(arquivoAudiencia);

    }
}
