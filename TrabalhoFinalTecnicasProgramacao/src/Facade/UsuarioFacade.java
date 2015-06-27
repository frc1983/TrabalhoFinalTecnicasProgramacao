package Facade;

import Dao.DAOUsuario;
import Exception.ConnectionException;
import Domain.Usuario;
import Exception.PersistenceException;
import java.util.Collection;

public class UsuarioFacade {
    private final DAOUsuario daoUsuario;
    
    public UsuarioFacade(){
        daoUsuario = new DAOUsuario();
    }
    
    public Collection<Usuario> buscarTodos() throws ConnectionException, PersistenceException{
        return daoUsuario.getAll();
    }
    
    public Collection<Usuario> buscarTodosPorTipo(int tipo) throws ConnectionException, PersistenceException{
        return daoUsuario.getAllByType(tipo);
    }
    
    public Usuario buscarPorId(int id) throws ConnectionException, PersistenceException{
        return daoUsuario.getById(id);
    }
    
    public boolean cadastrarUsuario(Usuario usuario) throws ConnectionException, PersistenceException{
        return daoUsuario.insert(usuario);
    }
}
