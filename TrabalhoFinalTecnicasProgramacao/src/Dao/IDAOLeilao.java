package Dao;

import Domain.Leilao;
import Exception.ConnectionException;
import Exception.PersistenceException;
import java.util.Collection;

public interface IDAOLeilao{
    boolean insert(Leilao entity) throws ConnectionException, PersistenceException;
    Collection<Leilao> getAllByStatus(int typeId) throws ConnectionException, PersistenceException;
    Leilao getById(int id) throws ConnectionException, PersistenceException;
}
