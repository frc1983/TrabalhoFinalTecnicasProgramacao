package Dao;

import Domain.Usuario;
import Exception.ConnectionException;
import Exception.PersistenceException;
import java.util.Collection;

public interface IDAOUsuario{
    Collection<Usuario> getAll() throws ConnectionException, PersistenceException;
    boolean insert(Usuario entity) throws ConnectionException, PersistenceException;
    Collection<Usuario> getAllByType(int typeId) throws ConnectionException, PersistenceException;
    Usuario getById(int id) throws ConnectionException, PersistenceException;
}
