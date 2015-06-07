package Facade;

import Dao.DAOUsuario;
import Exception.ConnectionException;
import Domain.Usuario;
import java.util.Collection;

public class UsuarioFacade {
    private final DAOUsuario daoUsuario;
    
    public UsuarioFacade(){
        daoUsuario = new DAOUsuario();
    }
    
    public Collection<Usuario> buscarTodos() throws ConnectionException{
        return daoUsuario.getAll();
    }
    
    public boolean cadastrarUsuario(Usuario usuario) throws ConnectionException{
        return daoUsuario.novoUsuario(usuario);
    }
}
