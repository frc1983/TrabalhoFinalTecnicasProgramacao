package Dao;

import Domain.Lote;
import Exception.ConnectionException;
import Exception.PersistenceException;

public interface IDAOLote{
    int insert(Lote entity) throws ConnectionException, PersistenceException;
    Lote getById(int id) throws ConnectionException, PersistenceException;
}
