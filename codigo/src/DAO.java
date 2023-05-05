package src;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;

public class DAO<T extends IDAO> {
    private final static String baseUrl = "/filesSave";
    
    
    /** Método que salva um conjunto de dados em um arquivo
     * @param nomeArquivo
     * @param objeto
     * @throws IOException
     */
    public void save(String nomeArquivo, Collection<T> objeto) throws IOException {
        try {
            FileWriter arquivo = new FileWriter(nomeArquivo);
            PrintWriter gravarArq = new PrintWriter(arquivo);
            Iterator<T> iterador = objeto.iterator();

            while (iterador.hasNext()) {
                T obj = iterador.next();
                if (iterador.hasNext()) {
                    gravarArq.println(obj.stringSalvar());
                } else {
                    gravarArq.print(obj.stringSalvar());
                }
            }

            arquivo.close();
        } catch (IOException e) {
            throw e;
        }
    }

    
    /** Método de salvar um único dado do objeto em um arquivo
     * @param nomeArquivo
     * @param objeto
     * @throws IOException
     */
    public void save(String nomeArquivo, T objeto) throws IOException {
        try {
            FileWriter arquivo = new FileWriter(nomeArquivo);
            PrintWriter gravarArq = new PrintWriter(arquivo);
            gravarArq.printf(objeto.stringSalvar());
            arquivo.close();
        } catch (IOException e) {
            throw e;
        }
    }

    
    /** Método que adiciona uma linha no final do arquivo
     * @param nomeArquivo
     * @param objeto
     * @throws IOException
     */
    public void append(String nomeArquivo, T objeto) throws IOException {
        try {
            FileWriter arquivo = new FileWriter(nomeArquivo);
            PrintWriter gravarArq = new PrintWriter(arquivo);
            gravarArq.append("\n" + objeto.stringSalvar());
            arquivo.close();
        } catch (IOException e) {
            throw e;
        }
    }
}