package src;

import java.util.List;
import java.util.Map;

public class Relatorio {
    private final PlataformaStreaming plataforma;

    public Relatorio(PlataformaStreaming plataforma) {
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

    public String gerarRelatorioAvaliacao() {
        Cliente clienteTop = plataforma.getClientes().values().stream().findFirst().get();

        for(Map.Entry<String, Cliente> clientes : plataforma.getClientes().entrySet()) {
            if(clientes.getValue().getAvaliacoes().size() > clienteTop.getAvaliacoes().size())
                clienteTop = clientes.getValue();
        }

        return "O cliente que avaliou mais mídias foi o " + clienteTop.getLogin() + ", com " + clienteTop.getAvaliacoes().size() + " avalidadas";
    }
    
    public void gerarRelatorioMediaAvaliacao() {

    }

    public void gerarRelatorioDezMelhores(){

    }

    public void gerarRelatorioMaisVistas(){

    }

    public void gerarRelatorioDezMelhoresGenero(){

    }
    
    public void gerarRelatorioMaisVistasGenero(){

    }
}
