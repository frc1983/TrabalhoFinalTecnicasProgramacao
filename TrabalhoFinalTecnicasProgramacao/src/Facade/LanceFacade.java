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
    
    public Lance buscarMaiorPorLote(int idLote) throws ConnectionException, PersistenceException{
        return daoLance.getHighestByLot(idLote);
    }
    
    public int cadastrarLance(Lance lance) throws ConnectionException{
        return daoLance.insert(lance);
    }
}
