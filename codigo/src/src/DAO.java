package src;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

public class DAO<T extends IDAO<T>> {
    private final String urlArquivo;

    public DAO(String arquivo) {
        this.urlArquivo = arquivo;
    }

    /**
     * Método que salva um conjunto de dados em um arquivo
     * 
     * @param objeto Collection<T>
     * @throws IOException Caso haja algum problema durante a execução do método
     */
    public void save(Collection<T> objeto) throws IOException {
        FileWriter arquivo = new FileWriter(this.urlArquivo);
        arquivo.append("#Cadastro");
        for (T obj : objeto)
            arquivo.append("\n").append(obj.stringSalvar());

        arquivo.close();
    }

    /**
     * Método que salva um conjunto de Strings
     * 
     * @param objeto Collection<String>
     * @throws IOException Caso haja algum problema durante a execução do método
     */
    public void save(List<String> objeto) throws IOException {
        FileWriter arquivo = new FileWriter(this.urlArquivo);
        for (String obj : objeto)
            arquivo.append("\n").append(obj);

        arquivo.close();
    }

    /**
     * Método que adiciona uma linha no final do arquivo
     * 
     * @param objeto T
     * @throws IOException - Caso haja algum problema durante a inserção da linha ao
     *                     final do arquivo
     */
    public void append(T objeto) throws IOException {
        try (FileWriter arquivo = new FileWriter(urlArquivo, true)) {
            arquivo.append("\n").append(objeto.stringSalvar());
        } catch (IOException e) {
            throw e;
        }
    }

    public void appendData(String linha) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(urlArquivo, true));
        writer.write("\n" + linha);
        writer.close();
    }

    /**
     * Método responsavel por criar uma lista do tipo passado, carregando os dados
     * de um arquivo
     * 
     * @param objeto T
     * @throws IOException Caso haja algum problema durante a execução do método
     */
    public List<T> load(T objeto) throws IOException {
        List<T> lista = new Stack<>();

        try (BufferedReader leitura = new BufferedReader(new FileReader(this.urlArquivo))) {
            String linha;
            while ((linha = leitura.readLine()) != null && !linha.isEmpty()) {
                linha = linha.replace("ï»¿", "");

                if (!linha.contains("#")) {
                    lista.add(objeto.loadObject(linha));
                }
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
        return lista;
    }

    /**
     * Método responsavel por criar uma lista do tipo passado, carregando os dados
     * de um arquivo
     *
     * @throws IOException Caso haja algum problema durante a execução do método
     */
    public List<String> load() throws IOException {
        List<String> lista = new Stack<>();
        try (BufferedReader leitura = new BufferedReader(new FileReader(this.urlArquivo))) {
            String linha;
            while ((linha = leitura.readLine()) != null) {
                linha = linha.replace("ï»¿", "");
                if (!linha.contains("#")) {
                    lista.add(linha);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }
}