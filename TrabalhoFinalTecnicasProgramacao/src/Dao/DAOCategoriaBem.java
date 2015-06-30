package Dao;

import Connection.DBConnection;
import Domain.CategoriaBem;
import Exception.ConnectionException;
import Exception.PersistenceException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class DAOCategoriaBem implements IDAOCategoriaBem {
    Connection conn = null;
    ResultSet res = null;
    Statement sta = null;
    Collection<CategoriaBem> categorias;
    
    @Override
    public Collection<CategoriaBem> getAll() throws ConnectionException, PersistenceException{        
        try {
            categorias = new ArrayList<>();
            String sql = "SELECT * FROM CategoriaBem";
            conn = DBConnection.getInstance();
            sta = conn.createStatement();            
            res = sta.executeQuery(sql);
            while (res.next()) {
                categorias.add(new CategoriaBem(
                        res.getInt("ID"),
                        res.getString("CATEGORIA"))
                );
            }
        } catch (ConnectionException | SQLException ex) {
            throw new PersistenceException("Erro ao consultar categorias de bem.", ex.getCause());
        }
        finally {
            DBConnection.close(conn, sta, res);
        }
        
        return categorias;
    }
}
