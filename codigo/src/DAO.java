package src;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class DAO<T extends IDAO> {

    public void salvar(String nomeArquivo, T objeto) throws IOException {
        try {
            FileWriter arquivo = new FileWriter(nomeArquivo);
            PrintWriter gravarArq = new PrintWriter(arquivo);
            List<String> linhas = objeto.stringSalvar();

            linhas.forEach(linha -> gravarArq.printf(linha));

            arquivo.close();
        } catch (IOException e) {
            throw e;
        }
    }
}