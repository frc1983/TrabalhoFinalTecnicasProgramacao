package Facade;

import Dao.DAOBem;
import Domain.Bem;
import Exception.ConnectionException;
import Exception.PersistenceException;
import java.util.Collection;

public class BemFacade {
    private final DAOBem daoBem;
    
    public BemFacade(){
        daoBem = new DAOBem();
    }
    
    public Collection<Bem> buscarTodos() throws ConnectionException, PersistenceException{
        return daoBem.getAll();
    }
    
    public boolean cadastrarBem(Bem bem) throws ConnectionException{
        return daoBem.novoBem(bem);
    }
}
