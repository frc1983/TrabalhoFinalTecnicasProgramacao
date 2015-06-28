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
    
    public Collection<Bem> buscarTodosDisponiveis() throws ConnectionException, PersistenceException{
        return daoBem.getAllFree();
    }
    
    public int cadastrarBem(Bem bem) throws ConnectionException{
        return daoBem.insert(bem);
    }

    public Collection<Bem> buscarTodosPorLote(int id) throws PersistenceException, ConnectionException {
        return daoBem.getAllByLot(id);
    }
}
