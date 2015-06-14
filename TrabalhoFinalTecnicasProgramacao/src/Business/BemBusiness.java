package Business;

import Domain.Bem;
import Exception.BemException;

public class BemBusiness {
    public static void validaBem(Bem bem) throws BemException{
        
        if (bem.getDescricao() == null || bem.getDescricao().length() == 0 || bem.getDescricao().equals("")) {
            throw new BemException("Descrição inválida");
        }
        
        if (bem.getDescricaocompleta()== null || bem.getDescricaocompleta().length() == 0 || bem.getDescricaocompleta().equals("")) {
            throw new BemException("Descrição completa inválida");
        }
    }
}
