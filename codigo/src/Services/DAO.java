package src.Services;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

public class DAO<T extends IDAO> {
    private final String baseUrl;

    public DAO(String arquivo) {
        this.baseUrl = arquivo;
    }

    /**
     * Método que salva um conjunto de dados em um arquivo
     * 
     * @param objeto
     * @throws IOException
     */
    public void save(Collection<T> objeto) throws IOException {
        try {
            FileWriter arquivo = new FileWriter(this.baseUrl);
            arquivo.append("#Cadastro");
            for (T obj : objeto)
                arquivo.append("\n" + obj.stringSalvar());

            arquivo.close();
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * Método que adiciona uma linha no final do arquivo
     * 
     * @param objeto
     * @throws IOException
     */
    public void append(T objeto) throws IOException {
        try {
            FileWriter arquivo = new FileWriter(baseUrl, true);
            arquivo.append("\n" + objeto.stringSalvar());
            arquivo.close();
        } catch (IOException e) {
            throw e;
        }
    }
}