package Dao;

import Connection.DBConnection;
import Exception.ConnectionException;
import Domain.TipoUsuario;
import Exception.PersistenceException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class DAOTipoUsuario implements IDAOTipoUsuario {

    DBConnection dbConnection = new DBConnection();
    
    @Override
    public Collection<TipoUsuario> getAll() throws ConnectionException, PersistenceException {
        Collection<TipoUsuario> tipos = new ArrayList<>();
        Statement sta = null;
        ResultSet res = null;

        try {
            dbConnection.open();

            String sql = "SELECT * FROM TipoUsuario";

            sta = dbConnection.getInstance().createStatement();
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
            try {
                if (res != null && !res.isClosed()) {
                    res.close();
                }
                if (sta != null && !sta.isClosed()) {
                    sta.close();
                }
                if (dbConnection != null && dbConnection.isOpen()) {
                    dbConnection.close();
                }
            } catch (SQLException ex) {
                throw new ConnectionException(ex.getCause());
            }
        }

        return tipos;
    }
}
