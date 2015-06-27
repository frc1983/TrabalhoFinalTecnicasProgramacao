package Dao;

import Connection.DBConnection;
import Domain.CategoriaBem;
import Exception.ConnectionException;
import Exception.PersistenceException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class DAOCategoriaBem implements IDAOCategoriaBem {
    
    DBConnection dbConnection = new DBConnection();
    
    @Override
    public Collection<CategoriaBem> getAll() throws ConnectionException, PersistenceException{
        Collection<CategoriaBem> categorias = new ArrayList<>();
        Statement sta = null;
        ResultSet res = null;
        
        try {
            dbConnection.open();
            
            String sql = "SELECT * FROM CategoriaBem";
            
            sta = dbConnection.getInstance().createStatement();            
            res = sta.executeQuery(sql);
            while (res.next()) {
                categorias.add(new CategoriaBem(
                        res.getInt("ID"),
                        res.getString("CATEGORIA"))
                );
            }
        } catch (ConnectionException | SQLException ex) {
            throw new PersistenceException("Erro ao consultar Tipos de usuários.", ex.getCause());
        }
        finally {
            try {
                if(res != null && !res.isClosed())
                    res.close();
                if(sta != null && !sta.isClosed())
                    sta.close();
                if (dbConnection != null && dbConnection.isOpen()) {
                    dbConnection.close();
                }
            } catch (SQLException ex) {
                throw new ConnectionException(ex.getCause());
            }
        }
        
        return categorias;
    }
}
