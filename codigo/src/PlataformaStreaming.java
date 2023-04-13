package src;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class PlataformaStreaming {
    private String nome;
    private HashSet<Serie> series;
    private HashSet<Cliente> clientes;
    private Cliente clienteAtual;

    public Cliente login(String nomeUsuario, String senha) {

        for (Cliente cliente : this.clientes)
            if (cliente.getSenha() == senha && cliente.getNomeUsuario() == nomeUsuario)
                return cliente;

        return new Cliente();
    }

    public void adicionarSerie(Serie serie) {
        this.series.add(serie);
    }

    public void adicionarCliente(Cliente cliente) {
        this.clientes.add(cliente);
    }

    public List<Serie> filtrarPorGenero(String genero) {
        List<Serie> series = new ArrayList<Serie>();
        // for(Serie serie : this.series)
            
        return series;
    }

    public List<Serie> filtrarPorIdioma(String idioma) {
        return new ArrayList<Serie>();
    }

    public List<Serie> filtrarPorQtdEpisodios(String idioma) {
        return new ArrayList<Serie>();
    }

    public void registrarAudiencia(Serie serie) {

    }

    public void logoff() {
        this.clienteAtual = null;
    }

    public Serie buscaSerie(String nomeSerie) {
        return new Serie();
    }

}
