package Dao;

import Connection.DBConnection;
import Exception.ConnectionException;
import Domain.TipoUsuario;
import Exception.PersistenceException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class DAOTipoUsuario implements IDAOTipoUsuario {
    
    Connection conn = null;
    ResultSet res = null;
    Statement sta = null;
    Collection<TipoUsuario> tipos;

    @Override
    public Collection<TipoUsuario> getAll() throws ConnectionException, PersistenceException {
        try {
            tipos = new ArrayList<>();
            String sql = "SELECT * FROM TipoUsuario";

            conn = DBConnection.getInstance();
            sta = conn.createStatement();
            res = sta.executeQuery(sql);
            while (res.next()) {
                tipos.add(new TipoUsuario(
                        res.getInt("ID"),
                        res.getString("TIPO"))
                );
            }
        } catch (ConnectionException | SQLException ex) {
            throw new PersistenceException("Erro ao consultar Tipos de usuários.", ex.getCause());
        } finally {
            DBConnection.close(conn, sta, res);
        }

        return tipos;
    }
}
