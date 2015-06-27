package Dao;

import Domain.Lote;
import Exception.ConnectionException;
import Exception.PersistenceException;
import java.sql.Connection;

public interface IDAOLote{
    int insert(Connection dbConnection, Lote entity) throws ConnectionException, PersistenceException;
    Lote getById(int id) throws ConnectionException, PersistenceException;
}
