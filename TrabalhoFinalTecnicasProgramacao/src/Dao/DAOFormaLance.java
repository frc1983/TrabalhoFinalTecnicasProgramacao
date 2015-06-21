package Dao;

import Connection.ConnectionFactory;
import Connection.IConnection;
import Domain.FormaLance;
import Exception.ConnectionException;
import Exception.PersistenceException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class DAOFormaLance implements IDAOFormaLance {

    @Override
    public Collection<FormaLance> getAll() throws ConnectionException, PersistenceException {
        Collection<FormaLance> formasLance = new ArrayList<>();
        IConnection conn = null;
        Statement sta = null;
        ResultSet res = null;

        try {
            conn = ConnectionFactory.getInstance();

            String sql = "SELECT * FROM FORMALANCE";

            sta = conn.getConnection().createStatement();
            res = sta.executeQuery(sql);
            while (res.next()) {
                formasLance.add(new FormaLance(
                        res.getInt("ID"),
                        res.getString("FORMA"))
                );
            }
        } catch (ConnectionException | SQLException ex) {
            throw new PersistenceException("Erro ao consultar Formas de lance.", ex.getCause());
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

        return formasLance;
    }
}
