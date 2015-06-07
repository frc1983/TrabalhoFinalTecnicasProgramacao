package Dao;

import Connection.DBConnection;
import Exception.ConnectionException;
import Model.TipoUsuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class DAOTipoUsuario {
    public Collection<TipoUsuario> getAll() throws ConnectionException{
        try {
            Connection conn = DBConnection.getConnection();
            Collection<TipoUsuario> tipos = new ArrayList<>();
            String sql = "SELECT * FROM TipoUsuario";
            
            PreparedStatement sta = conn.prepareStatement(sql);
            
            ResultSet res = sta.executeQuery();
            while (res.next()) {
                tipos.add(new TipoUsuario(
                        res.getInt("ID"),
                        res.getString("TIPO"))
                );
            }
            res.close();
            sta.close();
            return tipos;
        } catch (SQLException ex) {
            throw new ConnectionException(ex.getCause());
        }
    }
}
