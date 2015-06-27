package Dao;

import Connection.DBConnection;
import Domain.FormaLance;
import Exception.ConnectionException;
import Exception.PersistenceException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class DAOFormaLance implements IDAOFormaLance {

    DBConnection dbConnection = new DBConnection();
    
    @Override
    public Collection<FormaLance> getAll() throws ConnectionException, PersistenceException {
        Collection<FormaLance> formasLance = new ArrayList<>();
        Statement sta = null;
        ResultSet res = null;

        try {
            dbConnection.open();

            String sql = "SELECT * FROM FORMALANCE";

            sta = dbConnection.getInstance().createStatement();
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
                if (dbConnection != null && dbConnection.isOpen()) {
                    dbConnection.close();
                }
            } catch (SQLException ex) {
                throw new ConnectionException(ex.getCause());
            }
        }

        return formasLance;
    }

    @Override
    public FormaLance getById(int id) throws ConnectionException, PersistenceException {
        FormaLance formaLance = null;
        Statement sta = null;
        ResultSet res = null;

        try {
            dbConnection.open();

            String sql = "SELECT * FROM FORMALANCE";

            sta = dbConnection.getInstance().createStatement();
            res = sta.executeQuery(sql);
            while (res.next()) {
                formaLance = new FormaLance(
                        res.getInt("ID"),
                        res.getString("FORMA")
                );
            }
        } catch (ConnectionException | SQLException ex) {
            throw new PersistenceException("Erro ao consultar Forma de lance.", ex.getCause());
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

        return formaLance;
    }
}
