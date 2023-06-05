package src;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Relatorio {
    private final Audiovisual audiovisual;
    private final PlataformaStreaming plataforma;

    public Relatorio(Audiovisual audiovisual, PlataformaStreaming plataforma) {
        this.audiovisual = audiovisual;
        this.plataforma = plataforma;
    }

    public String gerarRelatorioDeMidia() {
        Cliente clienteTop = plataforma.getClientes().values().stream().findFirst().get();

        for(Map.Entry<String, Cliente> clientes : plataforma.getClientes().entrySet()) {
            if(clientes.getValue().getAssistidas().size() > clienteTop.getAssistidas().size())
                clienteTop = clientes.getValue();
        }

        return "O cliente que assistiu mais mídias foi o " + clienteTop.getLogin() + ", com " + clienteTop.getAssistidas().size() + " assistidos";
    }

    public void gerarRelatorioAvaliacao() {

    }
}
