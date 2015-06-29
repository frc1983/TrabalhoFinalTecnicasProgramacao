package Facade;

import Dao.DAOLance;
import Domain.Lance;
import Exception.ConnectionException;
import Exception.PersistenceException;
import java.util.Collection;

public class LanceFacade {
    private final DAOLance daoLance;
    
    public LanceFacade(){
        daoLance = new DAOLance();
    }
    
    public Collection<Lance> buscarTodosPorLote(int idLote) throws ConnectionException, PersistenceException{
        return daoLance.getAllByLot(idLote);
    }
    
    public Lance buscarMelhorLancePorLote(int idLote, int idNatureza) throws ConnectionException, PersistenceException{
        return daoLance.getBestByLot(idLote, idNatureza);
    }
    
    public int cadastrarLance(Lance lance) throws ConnectionException{
        return daoLance.insert(lance);
    }
}
