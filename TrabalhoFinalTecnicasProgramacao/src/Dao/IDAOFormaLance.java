package Dao;

import Domain.FormaLance;
import Exception.ConnectionException;
import Exception.PersistenceException;
import java.util.Collection;

public interface IDAOFormaLance {

    Collection<FormaLance> getAll() throws ConnectionException, PersistenceException;
}
