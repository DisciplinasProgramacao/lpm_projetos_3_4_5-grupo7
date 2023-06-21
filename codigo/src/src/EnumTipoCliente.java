package src;

import java.io.InvalidClassException;
import java.time.LocalDate;
import java.util.HashMap;

public enum EnumTipoCliente implements IComentarista {
  REGULAR{
    public void comentar(Avaliacao avaliacao, String comentario) throws InvalidClassException{
        throw new InvalidClassException("Cliente não pode comentar");
  }
  },
  ESPECIALISTA,
  PROFISSIONAL;

  public void comentar(Avaliacao avaliacao, String comentario) throws InvalidClassException {
        avaliacao.comentar(comentario);
  }

  public EnumTipoCliente verificarEspecialista(HashMap<Integer, Audiovisual> listaJaVistas) {
        LocalDate dataAtual = LocalDate.now();
        LocalDate dataLimite = dataAtual.minusMonths(1);
        int contador = 0;
        for (Audiovisual audiovisual : listaJaVistas.values()) {
            contador += (audiovisual.getDataAssistido().isAfter(dataLimite)
                    || audiovisual.getDataAssistido().isEqual(dataLimite)) ? 1 : 0;
        }
        if(contador>= 5){
          System.out.println("Você se tornou um Cliente Especialista");
          return EnumTipoCliente.ESPECIALISTA;
        }else {
          return EnumTipoCliente.REGULAR;
        }
  
    }
}
