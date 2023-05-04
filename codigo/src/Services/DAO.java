package src.Services;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;

public class DAO<T extends IDAO> {
    private final static String baseUrl = "/filesSave";
    // public DAO(String nomeAquivo){

    // }

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