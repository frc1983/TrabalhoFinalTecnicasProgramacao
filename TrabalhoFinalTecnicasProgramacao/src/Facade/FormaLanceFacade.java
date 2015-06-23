package Facade;

import Dao.DAOFormaLance;
import Domain.FormaLance;
import Exception.ConnectionException;
import Exception.PersistenceException;
import java.util.Collection;

public class FormaLanceFacade {
    private final DAOFormaLance daoFormaLance;
    
    public FormaLanceFacade(){
        daoFormaLance = new DAOFormaLance();
    }
    
    public Collection<FormaLance> buscarTodos() throws ConnectionException, PersistenceException{
        return daoFormaLance.getAll();
    }
}
