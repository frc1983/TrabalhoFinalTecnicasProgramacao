package Facade;

import Dao.DAOUsuario;
import Exception.ConnectionException;
import Model.Usuario;

public class UsuarioFacade {
    private final DAOUsuario daoUsuario;
    
    public UsuarioFacade(){
        daoUsuario = new DAOUsuario();
    }
    
    public boolean cadastrarUsuario(Usuario usuario) throws ConnectionException{
        return daoUsuario.novoUsuario(usuario);
    }
}
