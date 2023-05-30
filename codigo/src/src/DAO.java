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
     * @param objeto
     * @throws IOException
     */
    public void save(Collection<T> objeto) throws IOException {
        try {
            FileWriter arquivo = new FileWriter(this.urlArquivo);
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
            FileWriter arquivo = new FileWriter(urlArquivo, true);
            arquivo.append("\n" + objeto.stringSalvar());
            arquivo.close();
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * método appendData que recebe um identificador (key), um campo (column)
     * e uma lista de dados (data) a serem adicionados ao arquivo CSV.
     * 
     * @apiNote Se o arquivo ainda não existir, ele cria um novo arquivo e escreve a
     *          linha contendo o identificador, campo e dados.
     * @apiNote Se o arquivo já existir, ele lê todas as linhas do arquivo e verifica
     *            se já há uma linha com o mesmo identificador e campo fornecidos.
     *            Se sim, atualiza os dados dessa linha com a nova lista de dados.
     *            Caso contrário, adiciona uma nova linha contendo o identificador,
     *            campo e dados.
     * @param key
     * @param column
     * @param data
     * @throws IOException
     * 
     * 
     */
    public void appendData(String key, String column, List<String> data) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(urlArquivo, true));

        // Escreve cada item da lista no arquivo CSV
        for (String item : data) {
            writer.write(key + ";" + column + ";" + item);
            writer.newLine();
        }

        writer.close();
    }

    /**
     * Método responsavel por criar uma lista do tipo passado, carregando os dados
     * de um arquivo
     * 
     * @param objeto
     * @throws IOException
     */
    public List<T> load(T objeto) throws IOException {
        List<T> lista = new Stack<T>();

        try (BufferedReader leitura = new BufferedReader(new FileReader(this.urlArquivo))) {
            String linha = "";
            while ((linha = leitura.readLine()) != null) {
                linha = linha.replace("ï»¿", "");

                if (!linha.contains("#")) {
                    lista.add(objeto.loadObject(linha));
                }
            }
            leitura.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }

    /**
     * Método responsavel por criar uma lista do tipo passado, carregando os dados
     * de um arquivo
     * 
     * @param objeto
     * @throws IOException
     */
    public List<String> load() throws IOException {
        List<String> lista = new Stack<String>();
        try (BufferedReader leitura = new BufferedReader(new FileReader(this.urlArquivo))) {
            String linha = "";
            while ((linha = leitura.readLine()) != null) {
                linha = linha.replace("ï»¿", "");
                if (!linha.contains("#")) {
                    lista.add(linha);
                }
            }
            leitura.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }
}