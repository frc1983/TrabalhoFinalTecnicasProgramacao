package Dao;

import Connection.ConnectionFactory;
import Connection.IConnection;
import Exception.ConnectionException;
import Domain.TipoUsuario;
import Exception.PersistenceException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class DAOTipoUsuario implements IDAOTipoUsuario {

    @Override
    public Collection<TipoUsuario> getAll() throws ConnectionException, PersistenceException {
        Collection<TipoUsuario> tipos = new ArrayList<>();
        IConnection conn = null;
        Statement sta = null;
        ResultSet res = null;

        try {
            conn = ConnectionFactory.getInstance();

            String sql = "SELECT * FROM TipoUsuario";

            sta = conn.getConnection().createStatement();
            res = sta.executeQuery(sql);
            while (res.next()) {
                tipos.add(new TipoUsuario(
                        res.getInt("ID"),
                        res.getString("TIPO"))
                );
            }
        } catch (Exception ex) {
            throw new PersistenceException("Erro ao consultar Tipos de usuários.", ex.getCause());
        } finally {
            try {
                if (res != null && !res.isClosed()) {
                    res.close();
                }
                if (sta != null && !sta.isClosed()) {
                    sta.close();
                }
                if (conn != null && !conn.getConnection().isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                throw new ConnectionException(ex.getCause());
            }
        }

        return tipos;
    }
}