package Dao;

import Domain.TipoUsuario;
import Exception.ConnectionException;
import Exception.PersistenceException;
import java.util.Collection;

public interface IDAOTipoUsuario{
    Collection<TipoUsuario> getAll() throws ConnectionException, PersistenceException;
}
