package Facade;

import Dao.DAOTipoUsuario;
import Dao.IDAOTipoUsuario;
import Domain.TipoUsuario;
import Exception.ConnectionException;
import Exception.PersistenceException;
import java.util.Collection;

public class TipoUsuarioFacade {
    private final IDAOTipoUsuario daoTipoUsuario;
    
    public TipoUsuarioFacade(){
        daoTipoUsuario = new DAOTipoUsuario();
    }
    
    public Collection<TipoUsuario> buscarTodos() throws ConnectionException, PersistenceException{
        return daoTipoUsuario.getAll();
    }
}
