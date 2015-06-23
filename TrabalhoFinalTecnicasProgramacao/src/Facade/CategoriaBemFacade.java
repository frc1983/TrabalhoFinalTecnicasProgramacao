package Facade;

import Dao.DAOCategoriaBem;
import Domain.CategoriaBem;
import Exception.ConnectionException;
import Exception.PersistenceException;
import java.util.Collection;

public class CategoriaBemFacade {
    private final DAOCategoriaBem daoCategoriaBem;
    
    public CategoriaBemFacade(){
        daoCategoriaBem = new DAOCategoriaBem();
    }
    
    public Collection<CategoriaBem> buscarTodos() throws ConnectionException, PersistenceException{
        return daoCategoriaBem.getAll();
    }
}
