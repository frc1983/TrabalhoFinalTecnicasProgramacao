package Business;

import Domain.Lance;
import Exception.LanceException;

public class LanceBusiness {

    public static void validaLance(Lance lance) throws LanceException {
        if(lance.getValor() == null || lance.getValor().intValue() == 0)
            throw  new LanceException("O lance deve ter um valor.");
    }
}
