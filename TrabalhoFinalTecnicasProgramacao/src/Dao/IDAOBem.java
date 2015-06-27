package Dao;

import Domain.Bem;
import Exception.ConnectionException;
import Exception.PersistenceException;
import java.util.Collection;

public interface IDAOBem{
    Collection<Bem> getAll() throws ConnectionException, PersistenceException;
    int insert(Bem entity) throws ConnectionException, PersistenceException;
    Collection<Bem> getAllFree() throws ConnectionException, PersistenceException;
    Collection<Bem> getAllById(int idBem) throws PersistenceException, ConnectionException;
}
