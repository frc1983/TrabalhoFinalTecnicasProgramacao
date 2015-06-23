package Facade;

import Dao.DAONatureza;
import Domain.Natureza;
import Exception.ConnectionException;
import Exception.PersistenceException;
import java.util.Collection;

public class NaturezaFacade {
    private final DAONatureza daoNatureza;
    
    public NaturezaFacade(){
        daoNatureza = new DAONatureza();
    }
    
    public Collection<Natureza> buscarTodos() throws ConnectionException, PersistenceException{
        return daoNatureza.getAll();
    }
}
