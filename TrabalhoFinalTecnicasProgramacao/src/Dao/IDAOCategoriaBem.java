package Dao;

import Domain.CategoriaBem;
import Exception.ConnectionException;
import Exception.PersistenceException;
import java.util.Collection;

public interface IDAOCategoriaBem {

    Collection<CategoriaBem> getAll() throws ConnectionException, PersistenceException;
}
