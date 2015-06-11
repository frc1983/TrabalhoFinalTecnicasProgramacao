package Dao;

import Connection.ConnectionFactory;
import Connection.IConnection;
import Exception.ConnectionException;
import Domain.TipoUsuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class DAOTipoUsuario {
    public Collection<TipoUsuario> getAll() throws ConnectionException{
        try {
            IConnection conn = ConnectionFactory.getInstance();
            Collection<TipoUsuario> tipos = new ArrayList<>();
            String sql = "SELECT * FROM TipoUsuario";
            
            Statement sta = conn.getConnection().createStatement();            
            ResultSet res = sta.executeQuery(sql);
            while (res.next()) {
                tipos.add(new TipoUsuario(
                        res.getInt("ID"),
                        res.getString("TIPO"))
                );
            }
            res.close();
            sta.close();
            conn.close();
            return tipos;
        } catch (SQLException ex) {
            throw new ConnectionException(ex.getCause());
        }
    }
}
