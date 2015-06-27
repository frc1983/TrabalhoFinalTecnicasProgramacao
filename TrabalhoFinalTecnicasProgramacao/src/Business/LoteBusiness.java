package Business;

import Domain.Lote;
import Exception.LoteException;

public class LoteBusiness {
    public static void validaLote(Lote lote) throws LoteException{
        if(lote.getBens().size() <= 0)
            throw  new LoteException("O lote deve ter ao menos um bem.");
        
        if(lote.getPreco() == null || lote.getPreco().intValue() == 0)
            throw  new LoteException("O lote deve ter um preço.");
    }
}
