package Dao;

import Connection.ConnectionFactory;
import Connection.IConnection;
import Domain.CategoriaBem;
import Exception.ConnectionException;
import Exception.PersistenceException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class DAOCategoriaBem {
    public Collection<CategoriaBem> getAll() throws ConnectionException, PersistenceException{
        Collection<CategoriaBem> categorias = new ArrayList<>();
        IConnection conn = null;
        Statement sta = null;
        ResultSet res = null;
        
        try {
            conn = ConnectionFactory.getInstance();
            
            String sql = "SELECT * FROM CategoriaBem";
            
            sta = conn.getConnection().createStatement();            
            res = sta.executeQuery(sql);
            while (res.next()) {
                categorias.add(new CategoriaBem(
                        res.getInt("ID"),
                        res.getString("CATEGORIA"))
                );
            }
        } catch (Exception ex) {
            throw new PersistenceException("Erro ao consultar Tipos de usuários.", ex.getCause());
        }
        finally {
            try {
                if(res != null && !res.isClosed())
                    res.close();
                if(sta != null && !sta.isClosed())
                    sta.close();
                if(conn != null && !conn.getConnection().isClosed())
                    conn.close();
            } catch (SQLException ex) {
                throw new ConnectionException(ex.getCause());
            }
        }
        
        return categorias;
    }
}
