package Dao;

import Connection.DBConnection;
import Domain.FormaLance;
import Exception.ConnectionException;
import Exception.PersistenceException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class DAOFormaLance implements IDAOFormaLance {

    Connection conn = null;
    ResultSet res = null;
    Statement sta = null;
    PreparedStatement preparedsta = null;
    Collection<FormaLance> formasLance;

    @Override
    public Collection<FormaLance> getAll() throws ConnectionException, PersistenceException {
        try {
            formasLance = new ArrayList<>();
            String sql = "SELECT * FROM FORMALANCE";
            conn = DBConnection.getInstance();
            sta = conn.createStatement();
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
            DBConnection.close(conn, sta, res);
        }

        return formasLance;
    }

    @Override
    public FormaLance getById(int id) throws ConnectionException, PersistenceException {
        try {
            String sql = "SELECT * FROM FORMALANCE WHERE ID = ?";
            conn = DBConnection.getInstance();
            preparedsta = conn.prepareStatement(sql);
            preparedsta.setInt(1, id);
            res = preparedsta.executeQuery();
            while (res.next()) {
                return new FormaLance(
                        res.getInt("ID"),
                        res.getString("FORMA")
                );
            }
        } catch (ConnectionException | SQLException ex) {
            throw new PersistenceException("Erro ao consultar Forma de lance.", ex.getCause());
        } finally {
            DBConnection.close(conn, preparedsta, res);
        }

        return null;
    }
}
