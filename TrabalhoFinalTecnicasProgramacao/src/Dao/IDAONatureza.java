package Dao;

import Domain.Natureza;
import Exception.ConnectionException;
import Exception.PersistenceException;
import java.util.Collection;

public interface IDAONatureza {

    Collection<Natureza> getAll() throws ConnectionException, PersistenceException;
    Natureza getById(int id) throws ConnectionException, PersistenceException;
}
