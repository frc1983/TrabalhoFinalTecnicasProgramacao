package Dao;

import Domain.Lance;
import Exception.ConnectionException;
import Exception.PersistenceException;
import java.util.Collection;

public interface IDAOLance{
    Collection<Lance> getAllByLot(int idLote) throws ConnectionException, PersistenceException;
    int insert(Lance entity) throws ConnectionException, PersistenceException;
    Lance getHighestByLot(int idLote) throws ConnectionException, PersistenceException;
}
