package Dao;

import Connection.DBConnection;
import Domain.Natureza;
import Exception.ConnectionException;
import Exception.PersistenceException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class DAONatureza implements IDAONatureza {

    Connection conn = null;
    ResultSet res = null;
    Statement sta = null;
    PreparedStatement preparedsta = null;
    Collection<Natureza> naturezas;
    
    @Override
    public Collection<Natureza> getAll() throws ConnectionException, PersistenceException {
        try {
            naturezas = new ArrayList<>();
            String sql = "SELECT * FROM Natureza";

            conn = DBConnection.getInstance();
            sta = conn.createStatement();
            res = sta.executeQuery(sql);
            while (res.next()) {
                naturezas.add(new Natureza(
                        res.getInt("ID"),
                        res.getString("Nome"))
                );
            }
        } catch (ConnectionException | SQLException ex) {
            throw new PersistenceException("Erro ao consultar naturezas.", ex.getCause());
        } finally {
            DBConnection.close(conn, sta, res);
        }

        return naturezas;
    }

    @Override
    public Natureza getById(int id) throws ConnectionException, PersistenceException {
        try {
            String sql = "SELECT * FROM Natureza WHERE ID = ?";

            conn = DBConnection.getInstance();
            preparedsta = conn.prepareStatement(sql);
            preparedsta.setInt(1, id);
            res = preparedsta.executeQuery();
            while (res.next()) {
                return new Natureza(
                        res.getInt("ID"),
                        res.getString("Nome")
                );
            }
        } catch (ConnectionException | SQLException ex) {
            throw new PersistenceException("Erro ao consultar natureza.", ex.getCause());
        } finally {
            DBConnection.close(conn, preparedsta, res);
        }

        return null;
    }
}
